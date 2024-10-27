package OnlineBankingSystem;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();
    private int accountCounter = 1000;

    public int createAccount(String name, String password, int pinCode) {
        Account account = new Account(accountCounter++, name, password, pinCode);
        accounts.put(account.getAccountId(), account);
        return account.getAccountId();
    }

    public Account login(int accountId, String password) {
        Account account = accounts.get(accountId);
        if (account != null && account.verifyPassword(password)) {
            return account;
        } else {
            throw new SecurityException("Virhe: Väärä tilinumero tai salasana.");
        }
    }

    public double getBalance(int accountId, int pin) {
        Account account = accounts.get(accountId);
        if (account != null) {
            return account.getBalance(pin);
        }
        System.out.println("Virhe: Tiliä ei löydy.");
        return 0;
    }

    public void deposit(int accountId, double amount, int pin) {
        Account account = accounts.get(accountId);
        if (account != null) {
            account.deposit(amount, pin);
        } else {
            System.out.println("Virhe: Tiliä ei löydy.");
        }
    }

    public void withdraw(int accountId, double amount, int pin) {
        Account account = accounts.get(accountId);
        if (account != null) {
            if (!account.withdraw(amount, pin)) {
                System.out.println("Virhe: Riittämätön saldo.");
            }
        } else {
            System.out.println("Virhe: Tiliä ei löydy.");
        }
    }
}
