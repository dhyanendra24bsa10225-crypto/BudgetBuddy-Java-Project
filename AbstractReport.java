package com.budgetbuddy.reporting;

import com.budgetbuddy.model.Transaction;
import java.util.List;

/**
 * Abstract class defining the common structure for all reports.
 * Used for Inheritance and Polymorphism.
 */
public abstract class AbstractReport {
    
    protected final List<Transaction> transactions;

    public AbstractReport(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Abstract method that must be implemented by all concrete report types.
     * This is the core of Polymorphism.
     */
    public abstract void generateReport();

    protected void printSeparator() {
        System.out.println("----------------------------------------------");
    }
}