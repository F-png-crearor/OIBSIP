public class Main {
    public static void main(String [] args) {
        Bank bank = new Bank();
        bank.addAccount(new Account("user1", "1234", "Ofentse", 7500));
        bank.addAccount(new Account("user2", "4321", "Chantell", 5000));
        bank.addAccount(new Account("user3", "1111", "Nelo", 1500));

        ATM atm = new ATM(bank);
        atm.start();
    }
    
}
