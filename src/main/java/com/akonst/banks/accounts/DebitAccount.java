package com.akonst.banks.accounts;

import com.akonst.banks.service.BanksException;

public class DebitAccount extends BankAccount {
    public double debitPercent;

    @Override
    public void chargePercentsAndCommissions(int monthsToCharge) {
        double chargingPercent = monthsToCharge * (debitPercent / 12) / 100;

        double chargingSum = balance * chargingPercent;
        refill(chargingSum);
    }

    @Override
    public double withdraw(double sum) throws BanksException {
        if (balance - sum < 0) {
            throw new BanksException("You can't take more money than there is in the account!");
        }

        if (owner.isIncorrectClient()) {
            throw new BanksException("Please, fill your address and passport data!");
        }

        return balance -= sum;
    }
}
