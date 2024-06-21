package com.example.becoderapi.monitoring.transactions;

public interface TransactionMonitorMBean {
    int getTransactionCount();
    void setTransactionCount(int transactionCount);
    void resetTransactionCount();
    void incrementTransactionCount();
}
