import java.util.ArrayList;

public  class Account {
    private final String userId;
    private final String pin;
    private final String holderName;
    private double balance;
    private final ArrayList<Transaction> history = new ArrayList<>();

    public Account(String userId, String pin, String holderName, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getUserId() { return userId; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public ArrayList<Transaction> getHistory() { return history; }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount, balance));

    }
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        history.add(new Transaction("Withdraw", amount, balance));
        return true;
    }

    public boolean transferOut(double amount, String toId) {
        if (amount > balance) {
            return  false;
        }
        balance -= amount;
        history.add(new Transaction("Transfer to  " + toId, amount, balance));
        return  true;
    }

    public void transferIn(double amount, String fromId) {
        balance += amount;
        history.add(new Transaction("Transfer from " + fromId, amount, balance));
    }
     
}
