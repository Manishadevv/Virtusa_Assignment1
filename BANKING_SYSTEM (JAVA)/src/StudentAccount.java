public class StudentAccount extends Accounts{
    private double dailyLimit;
    private double Todaywithdraw;

    public StudentAccount(String account_num, String name, double init_balance, String username, String password ){
        super(account_num,name,init_balance,username,password, "Student");
        this.dailyLimit = 1000;
        this.Todaywithdraw = 0;
    }
    @Override
    public void withdraw(double amount){
        if(Todaywithdraw + amount > dailyLimit){
            System.out.println("Daily limit Exceeds!");
        }else{
            super.withdraw(amount);
            Todaywithdraw += amount;
        }
    }
}
