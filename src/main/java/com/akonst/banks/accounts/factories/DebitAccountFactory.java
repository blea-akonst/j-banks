package com.akonst.banks.accounts.factories;

import com.akonst.banks.Bank;
import com.akonst.banks.accounts.DebitAccount;
import com.akonst.banks.clients.BankClient;

public class DebitAccountFactory {
    public static DebitAccount makeAccount(Bank bank, BankClient owner) {
        var account = new DebitAccount();

        account.bankName = bank.getBankName();
        account.owner = owner;
        account.debitPercent = bank.getDebitPercent();

        return account;
    }
}
