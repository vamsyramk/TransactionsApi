package com.altimetrik.transactionsapi.modal;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Transaction {

    @NonNull
    private double amount;
    @NonNull
    private LocalDateTime time;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", time=" + time +
                '}';
    }
}
