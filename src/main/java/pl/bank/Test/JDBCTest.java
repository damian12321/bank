package pl.bank.Test;

import pl.bank.entity.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
    public static void main(String[] args) {
        String jdbc = "jdbc:mysql://localhost:3306/spring_bank?useSSL=false&serverTimezone=UTC";
        String user = "user";
        String password = "user";
        Connection connection = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to DB");
            connection = DriverManager.getConnection(jdbc, user, password);
            System.out.println("Connection established");
            String sql;
            sql = "SELECT id, first_name, last_name FROM customers";
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String firstname = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                //Display values
                Customer customer = new Customer(firstname, lastName);
                customer.setId(id);
                System.out.println(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
