package com.akonst.banks.accounts;

import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;

public abstract class BankAccount {
    protected double balance;

    public int id;
    public int currentDate;
    public String bankName;
    public BankClient owner;

    public double refill(double sum) {
        return balance += sum;
    }

    public double immediatelyWithdraw(double sum) {
        return balance -= sum;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void chargePercentsAndCommissions(int monthsToCharge);

    public abstract double withdraw(double sum) throws BanksException;
}
