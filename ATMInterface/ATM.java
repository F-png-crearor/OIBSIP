import java.util.Scanner;

public class ATM {
    private final Bank bank;
    private final Scanner sc = new Scanner(System.in);

public ATM(Bank bank) {
    this.bank = bank;
}

public void start() {
    System.out.println("=== Welcome to the ATM ===");
    Account account = login1();
    if (account == null) {
        System.out.println("Too many failed attempts. Access denied.");
        return;
    }
    System.out.println("\nWelcome, " + account.getHolderName() + "!");
        menu(account);
        sc.close();
    }

    private Account login1() {
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.println("User ID: ");
            String id = sc.nextLine().trim();
            System.out.println("PIN: ");
            String pin = sc.nextLine().trim();
            Account acc = bank.authenticate(id, pin);
            if (acc != null) {
                return acc;
            }
            System.out.println("Invaild User ID or PIN. Attempts left: " + (3 - attempt));
        }
        return null;
    }
    private void menu(Account account) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer ");
            System.out.println("5. Quit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: 
                showHistory(account);
                break;
                case 2:
                    withdraw(account);
                    break;
                    case 3:
                        deposit(account);
                        break;
                        case 4:
                            transfer(account);
                            break;
                            case 5:
                                System.out.println("Thank you for using the ATM. Goodbye!");
                                running = false;
                                break;
                                default:
                                    System.out.println("Invalid option. Please choose 1-5.");

            }
            
        }
    }

     private void showHistory(Account account) {
        System.out.println("\n--- Transaction History ---");
        if (account.getHistory().isEmpty()) {
            System.out.println("No transactions History ---");
        } else {
            for (Transaction t :  account.getHistory()) {
                System.out.println(t);
            }
        }
        System.out.printf("Current balance: R%.2f%n" , account.getBalance());
     }
     private void withdraw(Account account) {
        double amount = readAmount("Enter amount to deposit: R");
        if (account.withdraw(amount)) {
        System.out.printf("Withdrawal successful. New balance: R%.2f%n", account.getBalance());
     }else {
        System.out.println("Insufficient Funds");
     }
    }

private void deposit(Account account) {
    double amount = readAmount("Enter amount to deposit: R");
    account.deposit(amount);
    System.out.printf("Deposit successful. New balance: R%.2f%n", account.getBalance());
}
private void transfer(Account account) {
    System.out.println("Enter receipient account ID: ");
    String toId = sc.nextLine().trim();
    Account receipient = bank.findAccount(toId);

    if (receipient == null) {
        System.out.println("Recipient account not found.");
        return;
    }
    if (receipient == account) {
        System.out.println("You cannot transfer to your own account.");
        return;
    }

    double amount = readAmount("Enter amount to transfer: R");
    if (account.transferOut(amount, toId)) {
        receipient.transferIn(amount, account.getUserId());
        System.out.printf("Transfer successful. New balance: R%.2f%n", account.getBalance());
    } else {
        System.out.println("Insufficient Funds");
    }
}
private int readInt(String prompt) {
    System.out.print(prompt);
    try {
        return Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
        return -1;
    }
}
private double readAmount(String prompt) {
    while (true) {
        System.out.print(prompt);
        try {
            double value = Double.parseDouble(sc.nextLine().trim());
            if (value > 0) {
                return value; 
            }
            System.out.println("Amount must be greater than 0.");
    } catch (NumberFormatException e) {
        System.out.println("Invalid amount. Enter a number.");
    }
}
}
}

