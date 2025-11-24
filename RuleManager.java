package com.budgetbuddy.service;

import com.budgetbuddy.model.Transaction;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Applies categorization rules to a list of transactions.
 */
public class RuleManager {

    // Simple Map to store rules: Keyword -> Category Name
    private final Map<String, String> rules;

    public RuleManager() {
        this.rules = new HashMap<>();
        // Define default rules (can be loaded from a file for persistence later)
        rules.put("STARBUCKS", "Coffee");
        rules.put("RENT", "Housing");
        rules.put("SALARY", "Income");
        rules.put("AMAZON", "Shopping");
        rules.put("GROCERY", "Groceries");
    }

    /**
     * Iterates over all transactions and applies categorization rules.
     */
    public void categorize(List<Transaction> transactions) {
        System.out.println("\n--- Starting Rule-Based Categorization ---");
        int categorizedCount = 0;

        for (Transaction t : transactions) {
            String description = t.getDescription().toUpperCase();
            boolean matched = false;
            
            for (Map.Entry<String, String> entry : rules.entrySet()) {
                String keyword = entry.getKey();
                String category = entry.getValue();

                if (description.contains(keyword)) {
                    t.setCategory(category);
                    categorizedCount++;
                    matched = true;
                    break; 
                }
            }
            if (!matched && t.getType().equals("INCOME")) {
                t.setCategory("MISC_INCOME");
                categorizedCount++;
            }
        }
        System.out.println("Categorized " + categorizedCount + " out of " + transactions.size() + " transactions.");
    }
}