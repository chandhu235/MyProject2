import java.util.Scanner;

// Abstraction
abstract class Account {
    private String accountHolder; // Encapsulation
    private double balance;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    abstract void calculateInterest(); // Abstraction

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New Balance = " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New Balance = " + balance);
        } else {
            System.out.println("❌ Insufficient Balance!");
        }
    }
}

// Inheritance
class SavingsAccount extends Account {
    public SavingsAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    // Polymorphism
    @Override
    void calculateInterest() {
        double interest = getBalance() * 0.04;
        System.out.println("Savings Account Interest = " + interest);
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    // Polymorphism
    @Override
    void calculateInterest() {
        double interest = getBalance() * 0.02;
        System.out.println("Current Account Interest = " + interest);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to MyBank!");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.println("Choose Account Type: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int choice = sc.nextInt();

        Account account;
        if (choice == 1) {
            account = new SavingsAccount(name, balance);
        } else {
            account = new CurrentAccount(name, balance);
        }

        // Menu driven program
        int option;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;

                case 2:
                    System.out.print("Enter withdraw amount: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;

                case 3:
                    System.out.println("Current Balance = " + account.getBalance());
                    break;

                case 4:
                    account.calculateInterest();
                    break;

                case 5:
                    System.out.println("Thank you for using MyBank!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (option != 5);

        sc.close();
    }
}
