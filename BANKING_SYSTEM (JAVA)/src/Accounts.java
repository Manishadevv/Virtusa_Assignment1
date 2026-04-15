import util.DBconnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDateTime;
public abstract class Accounts {
    private String account_num;
    private String name;
    private double balance;
    private String username;
    private String password;
    private String acc_type;


    public Accounts(String account_num, String name, double init_balance, String username, String password, String acc_type) {
        this.account_num = account_num;
        this.name = name;
        this.balance = init_balance;
        this.username = username;
        this.password = password;
        this.acc_type = acc_type;
    }

    public String getAccount_num() {
        return account_num;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getAcc_type(){ return acc_type;
    }
    public void deposit(double amount){
      if(amount <= 0){
          System.out.print("Amount should be greater than Rs.0 !");
          return;
      }
      String sqlQuery = "update accounts set balance = balance + ?  where account_num =?";
      try(Connection connect = DBconnectionUtil.getConnection();
          PreparedStatement p = connect.prepareStatement(sqlQuery)){

          p.setDouble(1,amount);
          p.setString(2, this.account_num);
          int rows = p.executeUpdate();

          if(rows>0){
              this.balance += amount;
              System.out.println("Amount added to your acc : Rs." +amount);
              addTransaction("Deposit" , amount);
          }
      }catch(Exception e){
            e.printStackTrace();
      }

    }
    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Amount should be greater than Rs.0 !");

        }else if(this.balance < amount){
            System.out.println("Insufficent Balance!");

        }else{
            String sqlQuery = "update accounts set balance = balance - ?  where account_num =?";
            try(Connection connect = DBconnectionUtil.getConnection();
                PreparedStatement p = connect.prepareStatement(sqlQuery)){

                p.setDouble(1,amount);
                p.setString(2, this.account_num);
                int rows = p.executeUpdate();

                if(rows>0){
                    this.balance += amount;
                    System.out.println("Amount taken from your acc : Rs." +amount);
                    addTransaction("Withdraw" , amount);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void transfer(Accounts otherAccount, double amount){
        if(amount <= 0){
            System.out.println("Amount should be greater than Rs.0 !");

        }else if(amount > this.balance){
            System.out.println("Insufficinet balance");

        }else {
            withdraw(amount);
            otherAccount.deposit(amount);
            System.out.println("Rs." + amount + " transferred to " +otherAccount.getName() +" Successfully!");
            addTransaction("Transfer to "+ otherAccount.getName(), amount);
        }

    }

    public void addTransaction(String type, double amount){
       String sqlQuery= "Insert into transactions (account_num, action, amount, date) values (?,?,?,?)";
       try(Connection connect = DBconnectionUtil.getConnection();
       PreparedStatement p = connect.prepareStatement(sqlQuery)){
       p.setString(1,this.account_num);
       p.setString(2,acc_type);
       p.setDouble(3,amount);
       p.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
       p.executeUpdate();
       }catch(Exception e){
        e.printStackTrace();
       }
    }

    public void printHistory() {
        String sqlQuery = "select action, amount, date from transactions where account_num =? order by date desc";
        try(Connection connect = DBconnectionUtil.getConnection();
        PreparedStatement p = connect.prepareStatement(sqlQuery)){
            p.setString(1,getAccount_num());
            ResultSet r = p.executeQuery();
            System.out.println("----Transaction History------");

            boolean isTrans = false;
            while(r.next()){
                isTrans = true;
                String action = r.getString("action");
                double amt = r.getDouble("amount");
                java.sql.Timestamp date = r.getTimestamp("date");
                System.out.println("[" + date + "] " + action + " : Rs." + amt);
            }
            if(!isTrans){
                System.out.println("No transactions");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
