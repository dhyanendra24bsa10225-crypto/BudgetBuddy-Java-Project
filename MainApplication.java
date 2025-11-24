package com.budgetbuddy;

import com.budgetbuddy.model.Transaction;
import com.budgetbuddy.util.CsvParser;
import com.budgetbuddy.service.RuleManager;
import com.budgetbuddy.reporting.AbstractReport;
import com.budgetbuddy.reporting.MonthlySummaryReport;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList; // Added explicit import for ArrayList

/**
 * The main application driver for BudgetBuddy.
 * Handles the menu and workflow (NFR: Usability).
 */
public class MainApplication {
    
    // Initialized to an empty list for safety and robustness
    private static List<Transaction> transactions = new ArrayList<>(); 
    private static final CsvParser parser = new CsvParser();
    private static final RuleManager ruleManager = new RuleManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SAMPLE_FILE = "data/sample_transactions.csv";

    public static void main(String[] args) {
        System.out.println("--- Welcome to BudgetBuddy: Local Financial Analyzer ---");
        
        // Initial data loading
        loadData(SAMPLE_FILE);

        int choice;
        do {
            displayMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        viewTransactions();
                        break;
                    case 2:
                        ruleManager.categorize(transactions);
                        break;
                    case 3:
                        // Demonstrate Polymorphism
                        generateReport(new MonthlySummaryReport(transactions)); 
                        break;
                    case 0:
                        System.out.println("Exiting BudgetBuddy. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from the menu.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input (Error Handling)
                choice = -1;
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n==============================================");
        System.out.println("Current Transactions: " + transactions.size() + " records");
        System.out.println("==============================================");
        System.out.println("1. View All Transactions");
        System.out.println("2. Run Categorization Rules");
        System.out.println("3. Generate Monthly Summary Report");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void loadData(String filePath) {
        System.out.println("Attempting to load data from: " + filePath);
        transactions = parser.parseTransactions(filePath);
        if (transactions.isEmpty()) {
            System.err.println("WARNING: No transactions loaded. Cannot run reports.");
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }
        System.out.println("\n--- All Transactions (Top 10) ---");
        for (int i = 0; i < Math.min(10, transactions.size()); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
        if (transactions.size() > 10) {
            System.out.println("... showing only first 10 records.");
        }
    }
    
    // Demonstrates Polymorphism: the method accepts any class extending AbstractReport
    private static void generateReport(AbstractReport report) {
        if (transactions.isEmpty()) {
            System.out.println("Cannot generate report: Data not loaded.");
            return;
        }
        report.generateReport();
    }
}