package com.akonst.banks.accounts;

import com.akonst.banks.service.BanksException;

public class CreditAccount extends BankAccount {
    public double creditLimit;
    public double creditComission;

    @Override
    public void chargePercentsAndCommissions(int monthsToCharge) {
        immediatelyWithdraw(creditComission * monthsToCharge);
    }

    @Override
    public double withdraw(double sum) throws BanksException {
        if (balance < 0 && Math.abs(balance - sum) > creditLimit) {
            throw new BanksException("You can't take more money than there is in the account!");
        }

        if (!owner.isIncorrectClient()) {
            throw new BanksException("Please, fill your address and passport data!");
        }

        return balance -= sum;
    }
}
