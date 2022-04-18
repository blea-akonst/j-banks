package com.akonst.banks.accounts.factories;

import com.akonst.banks.Bank;
import com.akonst.banks.accounts.CreditAccount;
import com.akonst.banks.clients.BankClient;

public class CreditAccountFactory {
    public static CreditAccount makeAccount(Bank bank, BankClient owner) {
        var account = new CreditAccount();

        account.bankName = bank.getBankName();
        account.creditComission = bank.getCreditComission();
        account.creditLimit = bank.getCreditLimit();
        account.owner = owner;

        return account;
    }
}
