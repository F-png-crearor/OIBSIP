import java.util.ArrayList;

public class Bank {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String userId) {
        for (Account a : accounts) {
            if (a.getUserId().equals(userId)) {
                return a;

            }
        }
        return null;
    }
    public  Account authenticate(String userId, String pin) {
        Account a = findAccount(userId);
        if (a != null && a.checkPin(pin)) {
            return a;
        }
        return  null;
    }
    
}