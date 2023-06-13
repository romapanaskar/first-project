package io.qio.learning.firstproject;

public class AccountRequest {
    private int accountNumber;
    private  int amount;

    public AccountRequest(int accountNumber, int amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AccountRequest() {
    }
}
