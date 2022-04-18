package com.akonst.banks.clients;

public class BankClientBuilder {
    private String name;
    private String surname;
    private String address;
    private String passportNumber;

    public BankClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BankClientBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BankClientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BankClientBuilder setPassportNumber(String number) {
        this.passportNumber = number;
        return this;
    }

    public BankClient getClient() {
        return new BankClient(name, surname, address, passportNumber);
    }
}
