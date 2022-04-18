package com.akonst.banks.accounts;

import com.akonst.banks.service.BanksException;

public class DepositAccount extends BankAccount {
    public int depositExpiryDate;
    public double firstDepositPercent;
    public double secondDepositPercent;
    public double depositPercentIncreasingBorderSum;

    @Override
    public void chargePercentsAndCommissions(int monthsToCharge) {
        if (depositExpiryDate < currentDate) {
            return;
        }

        double percent = balance < depositPercentIncreasingBorderSum ? firstDepositPercent : secondDepositPercent;

        double monthlyPart = (percent / 12) / 100;
        double chargingPercent = monthsToCharge * monthlyPart;

        double chargingSum = balance * chargingPercent;
        refill(chargingSum);
    }

    @Override
    public double withdraw(double sum) throws BanksException {
        if (currentDate < depositExpiryDate) {
            throw new BanksException("The term of the deposit has not ended!");
        }

        if (balance - sum < 0) {
            throw new BanksException("Not enough money in the account!");
        }

        if (!owner.isIncorrectClient()) {
            throw new BanksException("Please, fill your address and passport data!");
        }

        return balance -= sum;
    }
}
