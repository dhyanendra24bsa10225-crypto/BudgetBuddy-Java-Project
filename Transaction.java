package com.budgetbuddy.model;

import java.time.LocalDate;

/**
 * Represents a single financial transaction.
 * Demonstrates Encapsulation (private fields, public getters).
 */
public class Transaction {
    private final LocalDate date;
    private final String description;
    private final double amount;
    private String category;
    private final String type; // INCOME or EXPENSE

    // Constructor
    public Transaction(LocalDate date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        
        // Determine type based on amount sign
        this.type = (amount >= 0) ? "INCOME" : "EXPENSE";
        this.category = "UNCATEGORIZED"; 
    }

    // Getters (Read-only access)
    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // Returns absolute value for display, sign determines type
    public double getAmount() {
        return Math.abs(amount);
    }
    
    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    // Setter for categorization (Controlled access)
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s (%.2f)", 
            date, category, description, getAmount()
        );
    }
}