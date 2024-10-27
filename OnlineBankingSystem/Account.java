package OnlineBankingSystem;

public class Account {
    private int accountId;
    private String accountHolder;
    private String passwordHash;
    private int pinCode;
    private double balance;

    public Account(int accountId, String accountHolder, String password, int pinCode) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.passwordHash = SecurityUtils.hashPassword(password); // Salasanan hash luodaan täällä
        this.pinCode = pinCode;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance(int pin) {
        if (pin == this.pinCode) {
            return balance;
        } else {
            throw new SecurityException("Väärä PIN-koodi!");
        }
    }

    public void deposit(double amount, int pin) {
        if (pin == this.pinCode) {
            if (amount > 0) {
                balance += amount;
            } else {
                System.out.println("Virhe: Talletettava summa ei voi olla negatiivinen.");
            }
        } else {
            throw new SecurityException("Väärä PIN-koodi!");
        }
    }

    public boolean withdraw(double amount, int pin) {
        if (pin == this.pinCode) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            } else {
                System.out.println("Virhe: Nostettava summa on virheellinen tai saldo ei riitä.");
                return false;
            }
        } else {
            throw new SecurityException("Väärä PIN-koodi!");
        }
    }

    public boolean verifyPassword(String password) {
        // Verrataan annettua salasanaa hashina tallennettuun hash-arvoon
        return this.passwordHash.equals(SecurityUtils.hashPassword(password));
    }
}
