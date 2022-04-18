package com.akonst.banks_tests;

import com.akonst.banks.*;
import com.akonst.banks.accounts.*;
import com.akonst.banks.accounts.factories.*;
import com.akonst.banks.clients.*;
import com.akonst.banks.service.BanksException;

import org.junit.jupiter.api.*;

public class BanksTest {
    private static CentralBank centralBank = new CentralBank();

    private DebitAccount accountBankOneDebit;
    private CreditAccount accountBankTwo;

    @BeforeEach
    public void setUp() throws BanksException {
        reset();

        var bankBuilder = new BankBuilder();

        double depositPercentFirst = 5;
        double depositPercentSecond = 7;
        Bank bankOne = bankBuilder.setBankName("Tinkoff Bank")
                .setCreditCommission(2000)
                .setDebitPercent(3)
                .setCreditLimit(200000)
                .setFirstDepositPercent(depositPercentFirst)
                .setSecondDepositPercent(depositPercentSecond)
                .setDepositPercentIncreasingBorderSum(50000)
                .setUntrustedClientTransactionLimit(15000)
                .getBank();
        centralBank.addBank(bankOne);

        Bank bankTwo = bankBuilder.setBankName("Alfa-Bank")
                .setCreditCommission(3000)
                .setDebitPercent(4)
                .setCreditLimit(100000)
                .setFirstDepositPercent(depositPercentFirst)
                .setSecondDepositPercent(depositPercentSecond)
                .setDepositPercentIncreasingBorderSum(100000)
                .setUntrustedClientTransactionLimit(10000)
                .getBank();
        centralBank.addBank(bankTwo);

        var clientBuilder = new BankClientBuilder();

        BankClient client;

        client = clientBuilder.setName("Mykola")
                .setSurname("Scherbak")
                .setAddress("Ukraine, Kyiv, Khreschatik st., bld. 5")
                .setPassportNumber("UA-ZEKRUTO")
                .getClient();

        client = bankOne.addClient(client);

        accountBankOneDebit = DebitAccountFactory.makeAccount(bankOne, client);
        bankOne.addAccount(accountBankOneDebit);

        client = clientBuilder.setName("Azamat")
                .setSurname("Kayratov")
                .setAddress("Republic of Kazakhstan, Almaty, Al-Farabi avenue, bld. 5")
                .setPassportNumber("KZ-NZRBVSSHL")
                .getClient();

        client = bankTwo.addClient(client);

        accountBankTwo = CreditAccountFactory.makeAccount(bankTwo, client);
    }

    @Test
    public void createClientAndRefillHimBalance_BalanceIsCorrect() {
        Assertions.assertEquals(0, accountBankOneDebit.getBalance());
        double refillSum = accountBankOneDebit.refill(456);
        Assertions.assertEquals(refillSum, accountBankOneDebit.getBalance());
    }

    @Test
    public void increaseTimeForOneMonth_ExpectedBalanceWithPercents() {
        double refillSum = accountBankOneDebit.refill(456);
        centralBank.increaseTime(31);
        Assertions.assertTrue(accountBankOneDebit.getBalance() > refillSum);
    }

    @Test
    public void moneyTransferFromFirstBankToSecond_SecondBankClientReceivedMoney() throws BanksException {
        accountBankOneDebit.refill(1000);
        centralBank.moneyTransfer(accountBankOneDebit, accountBankTwo, 1000);

        Assertions.assertEquals(0, accountBankOneDebit.getBalance());
        Assertions.assertEquals(1000, accountBankTwo.getBalance());
    }

    @Test
    public void clientInfoIsIncompleteAndHeTriesWithdrawMoney_ThrowsException() {
        accountBankOneDebit.owner.setPassportNumber("");
        accountBankOneDebit.refill(1000);

        Assertions.assertThrows(BanksException.class, () -> accountBankOneDebit.withdraw(1000));
    }

    @Test
    public void clientHasNotEnoughMoneyForWithdraw_ThrowsException() {
        accountBankOneDebit.refill(999);
        
        Assertions.assertThrows(BanksException.class, () -> accountBankOneDebit.withdraw(1000));
    }

    private void reset() {
        centralBank = new CentralBank();
    }
}