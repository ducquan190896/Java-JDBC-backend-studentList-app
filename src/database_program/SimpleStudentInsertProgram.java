package database_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameter;
import data_access.DbUtils;

public class SimpleStudentInsertProgram {
    
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scan = new Scanner(System.in);

        System.out.println("=== Add student ===");
        System.out.print("Id: ");
        int id = scan.nextInt();
        scan.nextLine();
        
        System.out.print("First name: ");
        String firstname = scan.next();
        scan.nextLine();

        System.out.print("Last name: ");
        String lastname = scan.next();
        scan.nextLine();

        System.out.print("Street: ");
        String streetaddress = scan.next();
        scan.nextLine();

        System.out.print("Postcode: ");
        String postcode = scan.next();
        scan.nextLine();

        System.out.print("Post office: ");
        String city = scan.next();
        scan.nextLine();
        
        try {
            String query = "INSERT into student (id, firstname, lastname,  streetaddress, postcode, city) values (?, ?, ?, ?, ?, ?)";
            connection = DriverManager.getConnection(ConnectionParameter.connectionString);

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, streetaddress);
            preparedStatement.setString(5, postcode);
            preparedStatement.setString(6, city);

            preparedStatement.executeUpdate();

            System.out.println("");
            System.out.println("Student data added!");

        } catch (SQLException e) {
            System.out.println("\n[ERROR] Database error. " + e.getMessage());
        } finally {
            DbUtils.closeQuitely(resultSet, preparedStatement, connection);
        }
        scan.close();

    }
}
