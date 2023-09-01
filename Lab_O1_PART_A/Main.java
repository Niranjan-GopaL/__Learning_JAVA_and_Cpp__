class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor to initialize the account attributes
    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
    
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
    
    }

    // Method to get the current account balance
    public double getBalance() {
        return balance;
    }

    // Method to display account information
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
    }

    // Method to transfer funds to another account
    public void transfer(BankAccount recipient, double amount) {
    
    }

    // Method to change the account holder's name
    public void changeAccountHolderName(String newHolderName) {
    }

    // Method to check if the account is in overdraft
    public boolean isOverdraft() {
    }

    // Method to apply interest to the account balance
    public void applyInterest(double interestRate) {
    }

    // Getter method for the account holder's name
    public String getAccountHolder() {
    }
}

public class Main {

    public static void main(String[] args) {
        // Create two bank accounts for testing
        BankAccount account1 = new BankAccount(1, "Ram", 1000.0);
        BankAccount account2 = new BankAccount(2, "Shyam", 1500.0);

        // Display initial account information
        System.out.println("Initial Account Information:");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        System.out.println();

        // Perform various operations on account1
        account1.deposit(500.0);
        account1.withdraw(200.0);
        account1.transfer(account2, 300.0);
        account1.changeAccountHolderName("Rana.");
        account1.applyInterest(3.5);

        // Display updated account information for account1
        System.out.println("Updated Account 1 Information:");
        account1.displayAccountInfo();
        System.out.println();

        // Display updated account information for account2
        System.out.println("Updated Account 2 Information:");
        account2.displayAccountInfo();
        System.out.println();

        // Check if account1 is in overdraft
        if (account1.isOverdraft()) {
            System.out.println("Account 1 is in overdraft.");
        } else {
            System.out.println("Account 1 is not in overdraft.");
        }
    }
}

