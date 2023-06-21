import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Create an instance of the ATM class
            ATM atm = new ATM();

            // Prompt for user id and pin
            System.out.print("Enter user ID: ");
            int userId = scanner.nextInt();
            System.out.print("Enter user PIN: ");
            int userPin = scanner.nextInt();

            // Attempt to log in
            boolean loggedIn = atm.login(userId, userPin);

            if (loggedIn) {
                System.out.println("Login successful.");
                atm.run();
            } else {
                System.out.println("Login failed. Invalid user ID or PIN.");
            }
        }
    }

    static class ATM {
        private int userId;
        private int userPin;
        private boolean loggedIn;
        private List<Transaction> transactionHistory;

        public ATM() {
            transactionHistory = new ArrayList<>();
        }

        public boolean login(int userId, int userPin) {
            // Perform login logic (e.g., validate against a database)
            // Set loggedIn to true if login is successful
            if (userId == 123 && userPin == 456) {
                this.userId = userId;
                this.userPin = userPin;
                loggedIn = true;
            } else {
                loggedIn = false;
            }
            return loggedIn;
        }

        public void run() {
            try (Scanner scanner = new Scanner(System.in)) {
                String choice;

                while (true) {
                    // Display menu options
                    System.out.println("\nATM Menu:");
                    System.out.println("1. Transactions History");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. Quit");

                    System.out.print("Enter your choice: ");
                    choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            displayTransactionHistory();
                            break;
                        case "2":
                            performWithdrawal();
                            break;
                        case "3":
                            performDeposit();
                            break;
                        case "4":
                            performTransfer();
                            break;
                        case "5":
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }

        private void displayTransactionHistory() {
            // Logic to retrieve and display transaction history
            System.out.println("Displaying transaction history...");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }

        private void performWithdrawal() {
            // Logic to perform a withdrawal
            System.out.println("Performing withdrawal...");
            // Example of adding a transaction to the history
            transactionHistory.add(new WithdrawalTransaction());
        }

        private void performDeposit() {
            // Logic to perform a deposit
            System.out.println("Performing deposit...");
            // Example of adding a transaction to the history
            transactionHistory.add(new DepositTransaction());
        }

        private void performTransfer() {
            // Logic to perform a transfer
            System.out.println("Performing transfer...");
            // Example of adding a transaction to the history
            transactionHistory.add(new TransferTransaction());
        }
    }

    static abstract class Transaction {
        private String type;
        private double amount;
        private String date;

        public Transaction(String type, double amount, String date) {
            this.type = type;
            this.amount = amount;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "type='" + type + '\'' +
                    ", amount=" + amount +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    static class WithdrawalTransaction extends Transaction {
        public WithdrawalTransaction() {
            super("Withdrawal", 100.0, "2023-06-21");
        }
    }

    static class DepositTransaction extends Transaction {
        public DepositTransaction() {
            super("Deposit", 200.0, "2023-06-21");
        }
    }

    static class TransferTransaction extends Transaction {
        public TransferTransaction() {
            super("Transfer", 300.0, "2023-06-21");
        }
    }
}