Problem Statement->
Modern banking users often struggle with unstructured financial data (typically raw CSV exports), which lacks categorization and meaningful analysis. This complexity makes it difficult to budget effectively and track spending trends. Furthermore, the reliance on third-party, cloud-based budgeting apps introduces privacy and security risks related to sharing sensitive financial information.
The core problem addressed by BudgetBuddy is the absence of a secure, local, and user-customizable tool built on strong OOP principles that can efficiently transform raw transaction data into actionable financial insights.

Scope of the Project->
BudgetBuddy is a console-based application developed in Java. The scope is limited to in-memory processing and console output, focusing on demonstrating core Java concepts:
1.	Local Data Processing: Reading transaction data exclusively from a local CSV file (data/sample_transactions.csv).
2.	Core Transactional Modules: Implementing and connecting classes for data parsing (CsvParser), categorization (RuleManager), and reporting.
3.	Core OOP Demonstration: Utilizing Encapsulation (Transaction class), Inheritance (AbstractReport), and Polymorphism (dynamic method dispatch for report generation).
4.	Error Handling: Implementation of robust exception handling for file I/O and data parsing errors.

Target Users->
1.	Students and Young Professionals: Individuals who need a clear, actionable way to track and control spending on a limited budget.
2.	Privacy-Conscious Users: Individuals who require a zero-cloud-upload solution to analyze sensitive financial data locally.
3.	Finance Enthusiasts: Users who want a customizable, extensible platform for personal financial modeling and reporting.

High-Level Features->
1.	Transaction Data Loading: Reads and parses structured data from a local CSV file into in-memory Java objects.
2.	Rule-Based Categorization: Automatically assigns transactions to predefined categories (e.g., 'Groceries', 'Coffee') based on keyword matching in the transaction description.
3.	Financial Reporting: Generates fundamental financial summaries, including total income, total expenses, and the calculated net savings or loss.
4.	Clear Input/Output Structure: Provides a simple, menu-driven console interface for smooth user interaction.

