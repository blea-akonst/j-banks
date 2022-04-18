package com.akonst.banks.transactions;

public class BankTransactionBuilder {
    private double sum;
    private int id;
    private int senderId;
    private int receiverId;
    private String sendersBank;
    private String receiversBank;

    public BankTransactionBuilder setSum(double sum) {
        this.sum = sum;
        return this;
    }

    public BankTransactionBuilder setTransactionId(int id) {
        this.id = id;
        return this;
    }

    public BankTransactionBuilder setSenderId(int id) {
        this.senderId = id;
        return this;
    }

    public BankTransactionBuilder setReceiverId(int id) {
        this.receiverId = id;
        return this;
    }

    public BankTransactionBuilder setSendersBank(String bank) {
        this.sendersBank = bank;
        return this;
    }

    public BankTransactionBuilder setReceiversBank(String bank) {
        this.receiversBank = bank;
        return this;
    }

    public BankTransaction getTransaction() {
        return new BankTransaction(id, sum, senderId, receiverId, sendersBank, receiversBank);
    }
}
