package com.akonst.banks;

import com.akonst.banks.clients.*;
import com.akonst.banks.service.BanksException;

import java.io.IOException;
import java.util.Scanner;


public class BanksConsoleInterface {
    public static void main(String[] args) throws BanksException, IOException {
        Scanner in = new Scanner(System.in);

        var centralBank = new CentralBank();
        var bankBuilder = new BankBuilder();
        var bankClientBuilder = new BankClientBuilder();

        Bank bank;
        int choose;

        System.out.println("Hello! Please, choose the option:");
        while (true)
        {
            printMenu();
            choose = in.nextInt();

            switch (choose) {
                case 1 -> {
                    System.out.println("Please, enter the bank name: ");
                    bankBuilder.setBankName(in.next());

                    System.out.println("Please, enter the debit percent: ");
                    bankBuilder.setDebitPercent(in.nextDouble());

                    System.out.println("Please, enter the credit commission: ");
                    bankBuilder.setCreditCommission(in.nextDouble());

                    System.out.println("Please, enter the credit limit (sum in roubles): ");
                    bankBuilder.setCreditLimit(in.nextDouble());

                    System.out.println("Please, enter the lower border deposit percent: ");
                    bankBuilder.setFirstDepositPercent(in.nextDouble());

                    System.out.println("Please, enter the upper border deposit percent: ");
                    bankBuilder.setSecondDepositPercent(in.nextDouble());

                    System.out.println(
                            "Please, enter the sum after which the percent on the deposit will increase: ");
                    bankBuilder.setDepositPercentIncreasingBorderSum(in.nextDouble());

                    System.out.println(
                            "Please, enter the transaction limit for client without pass or address data: ");
                    bankBuilder.setUntrustedClientTransactionLimit(in.nextDouble());

                    bank = bankBuilder.getBank();

                    System.out.println("A bank has been created with the following data: ");
                    printBankData(bank);

                    centralBank.addBank(bank);

                    break;
                }
                case 2 -> {
                    System.out.println("Please, enter the bank name which you like to add a client: ");
                    try {
                        bank = centralBank.getBank(in.next());
                    } catch (BanksException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Please, enter client's name:");
                    bankClientBuilder.setName(in.next());

                    System.out.println("Please, enter client's surname:");
                    bankClientBuilder.setSurname(in.next());

                    System.out.println("Please, enter client's address:");
                    bankClientBuilder.setAddress(in.next());

                    System.out.println("Please, enter client's passport number:");
                    bankClientBuilder.setPassportNumber(in.next());

                    bank.addClient(bankClientBuilder.getClient());

                    System.out.println("Client was successfully added!");

                    break;
                }
                case 3 -> {
                    System.out.println("Exit!");

                    in.close();
                    System.in.close();

                    System.exit(0);
                }
                default -> throw new BanksException("Incorrect argument!");
            }
        }
    }

    private static void printMenu()
    {
        System.out.println("1. Add bank");
        System.out.println("2. Add client to bank");
        System.out.println("3. Exit");
        System.out.print("Your choice: ");
    }

    private static void printBankData(Bank bank)
    {
        System.out.println("Bank name: " + bank.getBankName());
        System.out.println("Debit percent: " + bank.getDebitPercent());
        System.out.println("Credit commission: " + bank.getCreditComission());
        System.out.println("Credit limit: " + bank.getCreditLimit());
        System.out.println(
                "Deposit percent when balance is < " + bank.getDepositPercentIncreasingBorderSum() + ": " + bank.getFirstDepositPercent());
        System.out.println(
                "Deposit percent when balance is > " + bank.getDepositPercentIncreasingBorderSum() + ": " + bank.getSecondDepositPercent());
        System.out.println("Money transfer limit for untrusted account: " + bank.getUntrustedClientTransactionLimit());
    }
}
