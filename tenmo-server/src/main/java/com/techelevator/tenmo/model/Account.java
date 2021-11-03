package com.techelevator.tenmo.model;

public class Account {

    private long account_id;
    private long user_id;
    private long balance; //penny math

    public Account(){}


    public Account(long account_id, long user_id, long balance) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;

    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", balance=" + balance +
                '}';
    }
}
