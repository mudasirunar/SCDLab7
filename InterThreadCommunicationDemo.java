class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        notify(); // Notify waiting threads
    }
    public synchronized void withdraw(int amount) {
        while (balance < amount) {
            try {
                System.out.println("Insufficient balance. Waiting for deposit...");
                wait(); // Wait for deposit
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + ", Remaining Balance: " + balance);
    }
}
class DepositThread extends Thread {
    private BankAccount account;

    public DepositThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.deposit(500);
    }
}
class WithdrawThread extends Thread {
    private BankAccount account;

    public WithdrawThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.withdraw(1500);
    }
}
public class InterThreadCommunicationDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        
        WithdrawThread withdrawThread = new WithdrawThread(account);
        DepositThread depositThread = new DepositThread(account);
        
        withdrawThread.start();
        depositThread.start();
    }
}
