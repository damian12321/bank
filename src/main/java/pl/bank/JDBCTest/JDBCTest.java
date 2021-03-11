package pl.bank.JDBCTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {
    public static void main(String[] args) {
        String jdbc="jdbc:mysql://localhost:3306/spring_bank?useSSL=false&serverTimezone=UTC";
        String user="user";
        String password="user";
        try{
            System.out.println("Connecting to DB");
            Connection connection= DriverManager.getConnection(jdbc,user,password);
            System.out.println("Connection established");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
