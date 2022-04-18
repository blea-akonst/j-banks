package com.akonst.banks;

import com.akonst.banks.accounts.BankAccount;
import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Bank {
    private int currentDate;

    private List<BankClient> clients = new ArrayList<>();
    private List<BankAccount> accounts = new ArrayList<>();

    private int standardDepositTerm;
    private double debitPercent;
    private double firstDepositPercent;
    private double secondDepositPercent;
    private double depositPercentIncreasingBorderSum;
    private double creditComission;
    private double creditLimit;
    private double untrustedClientTransactionLimit;

    private String bankName;

    public Bank(String bankName,
                int standardDepositTerm,
                double debitPercent,
                double firstDepositPercent,
                double secondDepositPercent,
                double depositPercentIncreasingBorderSum,
                double creditComission,
                double creditLimit,
                double untrustedClientTransactionLimit) {
        this.bankName = bankName;
        this.standardDepositTerm = standardDepositTerm;
        this.debitPercent = debitPercent;
        this.firstDepositPercent = firstDepositPercent;
        this.secondDepositPercent = secondDepositPercent;
        this.depositPercentIncreasingBorderSum = depositPercentIncreasingBorderSum;
        this.creditComission = creditComission;
        this.creditLimit = creditLimit;
        this.untrustedClientTransactionLimit = untrustedClientTransactionLimit;
    }

    public void setCurrentDate(int date) {
        currentDate = date;

        for (BankAccount acc : accounts) {
            int prevDate = acc.currentDate;
            int monthsToCharge = ((prevDate % 30) + currentDate - prevDate) / 30;
            acc.chargePercentsAndCommissions(monthsToCharge);
            acc.currentDate = currentDate;
        }
    }

    public BankClient addClient(BankClient client) {
        clients.add(client);

        return client;
    }

    public void addAccount(BankAccount account) {
        int id = accounts.size();
        account.id = id;
        accounts.add(account);
    }

    public double refill(BankAccount account, double sum) {
        return account.refill(sum);
    }

    public double withdraw(BankAccount account, double sum) throws BanksException {
        return account.withdraw(sum);
    }

    public BankAccount getAccount(int id) throws BanksException {
        BankAccount account = null;

        for (BankAccount acc : accounts) {
            if (acc.id == id) {
                account = acc;
                break;
            }
        }

        if (account == null) {
            throw new BanksException("This account doesn't exists!");
        }

        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankName.equals(bank.getBankName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName);
    }

    public int getStandardDepositTerm() {
        return standardDepositTerm;
    }

    public double getDebitPercent() {
        return debitPercent;
    }

    public double getFirstDepositPercent() {
        return firstDepositPercent;
    }

    public double getSecondDepositPercent() {
        return secondDepositPercent;
    }

    public double getDepositPercentIncreasingBorderSum() {
        return depositPercentIncreasingBorderSum;
    }

    public double getCreditComission() {
        return creditComission;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getUntrustedClientTransactionLimit() {
        return untrustedClientTransactionLimit;
    }

    public String getBankName() {
        return bankName;
    }

    public int getCurrentDate() {
        return currentDate;
    }
}
