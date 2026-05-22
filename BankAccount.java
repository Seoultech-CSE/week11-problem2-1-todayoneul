import java.util.Scanner;
import java.util.InputMismatchException;

// 1. Custom Checked Exception Class
// TODO: Inherit from the correct class to make this a Checked Exception.
class InsufficientBalanceException extends Exception {
    private double balance;
    private double amount;

    public InsufficientBalanceException(double balance, double amount) {
        super("잔액이 부족합니다. 출금 요청 금액: $" + amount + ", 현재 잔액: $" + balance);
        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() { return balance; }
    public double getAmount() { return amount; }
}

// 2. Integrated Core Class
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        }

        balance += amount;
        System.out.println("$" + amount + "이(가) 성공적으로 입금되었습니다.");
    }

    // TODO: Add the proper exception declaration to the method signature[cite: 95].
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }

        balance -= amount;
        System.out.println("$" + amount + "이(가) 성공적으로 출금되었습니다.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount account = new BankAccount(500.0); 

        System.out.println("=== 은행 계좌 시스템에 오신 것을 환영합니다 ===");
        System.out.println("초기 잔액: $500.0");

        // --- DEPOSIT PROCESS ---
        // TODO: Wrap the deposit process in a try-catch-finally layout[cite: 34].
        // Catch InputMismatchException and IllegalArgumentException, and always display the balance[cite: 44, 151].
        try {
            System.out.print("\n입금할 금액을 입력하세요: ");
            double depositAmount = input.nextDouble();
            account.deposit(depositAmount);
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자로 된 입금 금액을 입력하세요.");
            input.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("현재 잔액: $" + account.getBalance());
        }


        // --- WITHDRAWAL PROCESS ---
        // TODO: Wrap the withdrawal process in a try-catch-finally layout[cite: 34].
        // Catch InputMismatchException and InsufficientBalanceException, and always display the balance[cite: 44, 151].
        try {
            System.out.print("\n출금할 금액을 입력하세요: ");
            double withdrawAmount = input.nextDouble();
            account.withdraw(withdrawAmount);
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자로 된 출금 금액을 입력하세요.");
            input.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("현재 잔액: $" + account.getBalance());
        }


        System.out.println("\n=== 이용해 주셔서 감사합니다 ===");
        input.close();
    }
}