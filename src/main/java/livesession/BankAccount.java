package livesession;

public class BankAccount {

    private double accountBalance;
    public static final int numAccounts;

    static {
        numAccounts = 0;
    }

    public void deposit(double deposit){

        this.accountBalance += deposit;
    }

    public void withdraw(double amount){

        if (amount >= this.accountBalance)
            this.accountBalance -= amount;
    }


}
