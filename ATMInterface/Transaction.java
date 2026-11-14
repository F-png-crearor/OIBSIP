import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
 
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String time;

    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")); 
    }

    @Override 
    public String toString() {
        return String.format("[%s] %-20s R%.2f | Balance: R%.2f", time, type, amount, balanceAfter);
    }
}
