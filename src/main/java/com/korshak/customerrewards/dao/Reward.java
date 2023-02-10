package com.korshak.customerrewards.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Table to store customer rewards.
 */
@Entity
public class Reward {
    @Id
    String transactionId;
    String customerID;
    Integer points;
    Long time;

    public Reward() {
    }

    public Reward(String transactionId, String customerID, Integer points, Long time) {
        this.transactionId = transactionId;
        this.customerID = customerID;
        this.points = points;
        this.time = time;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
