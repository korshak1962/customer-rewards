package com.korshak.customerrewards.controllers;

/**
 * Input value class for controller.
 */
public class CustomerTransaction {
    private String transactionID;
    private String CustomerID;
    private int amount;
    private long time;

    public CustomerTransaction(String transactionID, String customerID, int amount, long time) {
        this.transactionID = transactionID;
        CustomerID = customerID;
        this.amount = amount;
        this.time = time;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CustomerTransaction{" +
                "transactionID='" + transactionID + '\'' +
                ", CustomerID='" + CustomerID + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
