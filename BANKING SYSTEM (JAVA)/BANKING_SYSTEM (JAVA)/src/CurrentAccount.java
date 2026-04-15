public class CurrentAccount extends Accounts{
 private double overdraft;
 public CurrentAccount(String account_num, String name, double init_balance, String username, String password){
     super(account_num, name, init_balance, username, password, "Current" );
     this.overdraft = 1000;
 }
 @Override
 public void withdraw(double amount){
     if((getBalance() - amount) <= -overdraft){
         System.out.println("Overdraft limit exceeds");
         return;
     }
     super.withdraw(amount);
 }
}
