public class SavingsAccount extends Accounts{
    private double minBalance;
    public SavingsAccount(String account_num, String name, double init_balance, String username, String password){
        super(account_num, name, init_balance, username, password, "Savings");
        this.minBalance = 1000;
    }
    public void withdraw(double amount){
        if(getBalance() - amount < 1000){
            System.out.printf("Reached minimum balance!");
            return;
        }
        super.withdraw(amount);

    }
}
