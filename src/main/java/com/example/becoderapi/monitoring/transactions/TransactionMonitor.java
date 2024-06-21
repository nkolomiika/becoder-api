package com.example.becoderapi.monitoring.transactions;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example.becoder.monitoring.transactions:name=TransactionMonitor", description = "Transactions monitoring")
public class TransactionMonitor implements TransactionMonitorMBean {
    private int transactionCount;

    @Override
    @ManagedAttribute(description = "get number of transactions")
    public int getTransactionCount() {
        return transactionCount;
    }

    @ManagedAttribute(description = "Set number of transactions")
    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    @Override
    @ManagedOperation(description = "Reset transaction counter")
    public void resetTransactionCount() {
        transactionCount = 0;
    }

    @Override
    public void incrementTransactionCount() {
        transactionCount++;
    }
}
