package OnlineBankingSystem;

import java.util.Scanner;

public class OnlineBankingSystem {
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Online Banking System ---");
            System.out.println("1. Luo uusi tili");
            System.out.println("2. Kirjaudu sisään tilille");
            System.out.println("3. Poistu");
            System.out.print("Valitse toiminto: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Kiitos käytöstä! Hyvää päivän jatkoa.");
                    break;
                default:
                    System.out.println("Virheellinen valinta, yritä uudelleen.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Syötä nimi: ");
        scanner.nextLine(); // Tyhjennetään syöte
        String name = scanner.nextLine();

        System.out.print("Syötä salasana: ");
        String password = scanner.nextLine();

        System.out.print("Syötä nelinumeroinen PIN-koodi: ");
        int pinCode = scanner.nextInt();

        int accountId = bank.createAccount(name, password, pinCode);
        System.out.println("Tili luotu onnistuneesti! Tilin numero on: " + accountId);
    }

    private static void login(Scanner scanner) {
        System.out.print("Syötä tilinumero: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Tyhjennetään syöte

        System.out.print("Syötä salasana: ");
        String password = scanner.nextLine();

        try {
            Account account = bank.login(accountId, password);
            performAccountActions(scanner, account);
        } catch (SecurityException e) {
            System.out.println("Virheellinen tilinumero tai salasana.");
        }
    }

    private static void performAccountActions(Scanner scanner, Account account) {
        int choice;
        do {
            System.out.println("\n--- Tilitoiminnot ---");
            System.out.println("1. Tarkista saldo");
            System.out.println("2. Tee talletus");
            System.out.println("3. Tee nosto");
            System.out.println("4. Kirjaudu ulos");
            System.out.print("Valitse toiminto: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Syötä PIN-koodi: ");
                    int pin = scanner.nextInt();
                    try {
                        System.out.println("Tilisi saldo on: " + bank.getBalance(account.getAccountId(), pin));
                    } catch (SecurityException e) {
                        System.out.println("Virheellinen PIN-koodi.");
                    }
                    break;
                case 2:
                    System.out.print("Syötä PIN-koodi: ");
                    pin = scanner.nextInt();
                    System.out.print("Syötä talletettava summa: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        bank.deposit(account.getAccountId(), depositAmount, pin);
                        System.out.println("Talletus onnistui!");
                    } catch (SecurityException e) {
                        System.out.println("Virheellinen PIN-koodi.");
                    }
                    break;
                case 3:
                    System.out.print("Syötä PIN-koodi: ");
                    pin = scanner.nextInt();
                    System.out.print("Syötä nostettava summa: ");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        bank.withdraw(account.getAccountId(), withdrawAmount, pin);
                        System.out.println("Nosto onnistui!");
                    } catch (SecurityException e) {
                        System.out.println("Virheellinen PIN-koodi.");
                    }
                    break;
                case 4:
                    System.out.println("Kirjauduttu ulos tililtä.");
                    break;
                default:
                    System.out.println("Virheellinen valinta, yritä uudelleen.");
            }
        } while (choice != 4);
    }
}
