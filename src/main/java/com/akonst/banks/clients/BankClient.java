package com.akonst.banks.clients;

public class BankClient {
    private String currentBankInfo;
    private String name;
    private String surname;
    private String address;
    private String passportNumber;

    public BankClient(String name, String surname, String address, String passportNumber)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void updateInfo(String info) {
        currentBankInfo = info;
    }

    public boolean isIncorrectClient() {
        return address.isEmpty() || passportNumber.isEmpty();
    }
}
