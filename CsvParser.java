package com.budgetbuddy.util;

import com.budgetbuddy.model.Transaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading transaction data from a CSV file.
 * Includes robust Exception Handling (NFR: Reliability).
 */
public class CsvParser {
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String SEPARATOR = ",";

    public List<Transaction> parseTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        
        // Use try-with-resources for automatic resource management
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header line
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; 
                
                String[] values = line.split(SEPARATOR);
                
                // Basic validation (expecting 3 columns)
                if (values.length < 3) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    LocalDate date = LocalDate.parse(values[0].trim(), DATE_FORMAT);
                    String description = values[1].trim();
                    // Amount might be positive (credit) or negative (debit) in the file
                    double amount = Double.parseDouble(values[2].trim()); 
                    
                    transactions.add(new Transaction(date, description, amount));
                } catch (Exception e) {
                    System.err.println("Error parsing data fields in line: " + line + ". Details: " + e.getMessage());
                }
            }
            System.out.println("Successfully loaded " + transactions.size() + " transactions.");
            
        } catch (IOException e) {
            // Handles FileNotFoundException and other I/O errors
            System.err.println("\nERROR: Could not read file at " + filePath);
            System.err.println("Please check the file path and ensure the file exists.");
        }
        return transactions;
    }
}