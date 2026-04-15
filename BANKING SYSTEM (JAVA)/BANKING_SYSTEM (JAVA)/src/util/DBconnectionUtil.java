package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectionUtil {

    public static Connection getConnection(){
        Connection connect = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Dhak$hna02");

        } catch (Exception e) {
           e.printStackTrace();
        }
        return connect;
    }
    public static void closeConnection(){
        try {
            getConnection().close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
