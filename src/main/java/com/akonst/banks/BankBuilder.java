package com.akonst.banks;

public class BankBuilder {
    private Bank bank;
    
    private String bankName;
    private double debitPercent;
    private double firstDepositPercent;
    private double secondDepositPercent;
    private double depositPercentIncreasingBorderSum;
    private double creditComission;
    private double creditLimit;
    private double untrustedClientTransactionLimit;
    private int standardDepositTerm;

    public BankBuilder setBankName(String name) {
        this.bankName = name;
        return this;
    }

    public BankBuilder setDebitPercent(double percent) {
        this.debitPercent = percent;
        return this;
    }

    public BankBuilder setFirstDepositPercent(double percent) {
        this.firstDepositPercent = percent;
        return this;
    }

    public BankBuilder setSecondDepositPercent(double percent) {
        this.secondDepositPercent = percent;
        return this;
    }

    public BankBuilder setDepositPercentIncreasingBorderSum(double sum) {
        this.depositPercentIncreasingBorderSum = sum;
        return this;
    }

    public BankBuilder setCreditCommission(double sum) {
        this.creditComission = sum;
        return this;
    }

    public BankBuilder setCreditLimit(double sum) {
        this.creditLimit = sum;
        return this;
    }

    public BankBuilder setUntrustedClientTransactionLimit(double sum) {
        this.untrustedClientTransactionLimit = sum;
        return this;
    }

    public BankBuilder setStandardDepositTerm(int term) {
        this.standardDepositTerm = term;
        return this;
    }

    public Bank getBank() {
        return new Bank(bankName,
                        standardDepositTerm,
                        debitPercent,
                        firstDepositPercent,
                        secondDepositPercent,
                        depositPercentIncreasingBorderSum,
                        creditComission,
                        creditLimit,
                        untrustedClientTransactionLimit);
    }
}
