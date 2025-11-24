package com.budgetbuddy.reporting;

import com.budgetbuddy.model.Transaction;
import java.util.List;

/**
 * Concrete report class that generates a summary of income vs. expense.
 */
public class MonthlySummaryReport extends AbstractReport {

    public MonthlySummaryReport(List<Transaction> transactions) {
        super(transactions);
    }

    @Override
    public void generateReport() {
        double totalIncome = 0;
        double totalExpense = 0;
        
        for (Transaction t : transactions) {
            if (t.getType().equals("INCOME")) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
            }
        }
        
        double netFlow = totalIncome - totalExpense;

        System.out.println("\n*** Monthly Summary Report ***");
        super.printSeparator();
        System.out.printf("Total Income:  $%.2f\n", totalIncome);
        System.out.printf("Total Expenses: $%.2f\n", totalExpense);
        super.printSeparator();
        
        if (netFlow >= 0) {
            System.out.printf("Net Savings:    $%.2f (Healthy)\n", netFlow);
        } else {
            System.out.printf("Net Loss:       $%.2f (Warning)\n", netFlow);
        }
        super.printSeparator();
    }
}