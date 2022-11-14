package database_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Student;
import data_access.ConnectionParameter;
import data_access.DbUtils;

public class SimpleStudentSearchProgram {
    
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Scanner input = new Scanner(System.in);

        System.out.println("=== Find student by id ===");
        System.out.print("Enter student id: ");
        int inputId = input.nextInt();
        input.nextLine();
        try {
            connection = DriverManager.getConnection(ConnectionParameter.connectionString);
            String query = "select * from student where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inputId);

            resultSet = preparedStatement.executeQuery();
            boolean hasRow = false;
                while(resultSet.next()) {
                    hasRow = true;
                    Student student = new Student(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("postcode"), resultSet.getString("streetaddress"), resultSet.getString("city"));
                    System.out.println(student.toString());
                }
            
                if(hasRow == false)  {
                    System.out.println("Unknown student id (" + inputId + ")");
                }
           

        } catch (SQLException e) {
            System.out.println("\n[ERROR] Database error. " + e.getMessage());
        } finally {
            DbUtils.closeQuitely(resultSet, preparedStatement, connection);
        }
        input.close();

    }
}
