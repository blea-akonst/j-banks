package com.akonst.banks.accounts.factories;

import com.akonst.banks.Bank;
import com.akonst.banks.accounts.DepositAccount;
import com.akonst.banks.clients.BankClient;

public class DepositAccountFactory {
    public static DepositAccount makeAccount(Bank bank, BankClient owner, int depositExpiryDate) {
        var account = new DepositAccount();

        account.bankName = bank.getBankName();
        account.firstDepositPercent = bank.getFirstDepositPercent();
        account.secondDepositPercent = bank.getSecondDepositPercent();
        account.depositPercentIncreasingBorderSum = bank.getDepositPercentIncreasingBorderSum();
        account.depositExpiryDate = depositExpiryDate;
        account.owner = owner;

        return account;
    }
}
