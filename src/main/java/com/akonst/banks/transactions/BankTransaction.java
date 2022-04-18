package com.akonst.banks.transactions;

public class BankTransaction {
    private int id;
    private double sum;
    private int senderId;
    private int receiverId;
    private String sendersBank;
    private String receiversBank;

    public BankTransaction(int id, double sum, int senderId, int receiverId, String sendersBank, String receiversBank) {
        this.id = id;
        this.sum = sum;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sendersBank = sendersBank;
        this.receiversBank = receiversBank;
    }

    public int getId() {
        return id;
    }

    public double getSum() {
        return sum;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getSendersBank() {
        return sendersBank;
    }

    public String getReceiversBank() {
        return receiversBank;
    }
}
