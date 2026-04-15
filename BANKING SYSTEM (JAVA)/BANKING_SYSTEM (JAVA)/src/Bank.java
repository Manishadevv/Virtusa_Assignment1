import org.w3c.dom.ls.LSOutput;
import util.DBconnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    ArrayList<Accounts> acclist = new ArrayList();


    public void createAccount(String name, double balance, String username, String password, String acc_type) {
        Accounts acc;
        String account_num = "MDID" + (System.currentTimeMillis() % 100000);
        String sqlQuery = "Insert into accounts values(?,?,?,?,?,?)";
        try(Connection connect = DBconnectionUtil.getConnection();
            PreparedStatement p = connect.prepareStatement(sqlQuery)) {
            p.setString(1, account_num);
            p.setString(2, name);
            p.setDouble(3, balance);
            p.setString(4, username);
            p.setString(5, password);
            p.setString(6, acc_type);
            p.executeUpdate();
            System.out.println("Account created successfuly");
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public Accounts login (String username, String password) {
        String sqlQuery = "select * from accounts where username = ? and password =?";

        try (Connection connect = DBconnectionUtil.getConnection();
             PreparedStatement p = connect.prepareStatement(sqlQuery)) {
            p.setString(1, username);
            p.setString(2, password);

            ResultSet r = p.executeQuery();
            if (r.next()) {
                String type = r.getString("acc_type") ;
                if(type.equals("Savings") ) return new SavingsAccount(r.getString(1),r.getString(2),r.getDouble(3),username,password);
                if(type.equals("Current"))  return new CurrentAccount(r.getString(1),r.getString(2),r.getDouble(3),username,password);
                if(type.equals("Student"))  return new StudentAccount(r.getString(1),r.getString(2),r.getDouble(3),username,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;
    }

    public Accounts findbyaccnum (String account_num){
        for(Accounts a : acclist){
            if(a.getAccount_num().equals(account_num)){
                return a;
            }
        }
        System.out.println("no user found!");
        return null;
    }
}

