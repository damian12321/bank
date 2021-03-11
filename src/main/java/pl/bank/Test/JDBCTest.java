package pl.bank.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
    public static void main(String[] args) {
        String jdbc="jdbc:mysql://localhost:3306/spring_bank?useSSL=false&serverTimezone=UTC";
        String user="user";
        String password="user";
        Connection connection=null;
        Statement stmt=null;
        try{
            System.out.println("Connecting to DB");
            connection= DriverManager.getConnection(jdbc,user,password);
            System.out.println("Connection established");
            String sql;
            sql = "SELECT id, first_name, last_name FROM customers";
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String firstname = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First Name: " + firstname);
                System.out.print(", Last Name: " + lastName);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
