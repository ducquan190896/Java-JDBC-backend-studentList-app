package database_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameter;
import data_access.DbUtils;

public class SimpleStudentDeleteProgram {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Scanner scan = new Scanner(System.in);
        
        System.out.println("=== Delete student ===");
        System.out.print("Enter student id: ");
        int id = scan.nextInt();
        scan.nextLine();

        try {
            String query = "DELETE from student where id = ?";
            connection = DriverManager.getConnection(ConnectionParameter.connectionString);
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
           int rowReturn = preparedStatement.executeUpdate();
           if(rowReturn == 0) {
            System.out.println("Nothing deleted. Unknown student id (" + id + ")");
           } else {
            System.out.println("Student deleted!");
           }

            

        } catch(SQLException e) {
            System.out.println("\n[ERROR] Database error. " + e.getMessage());
        } finally {
            DbUtils.closeQuitely(resultSet, preparedStatement, connection);
        }

        scan.close();
    }
}
