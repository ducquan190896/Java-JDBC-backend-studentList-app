package database_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Student;
import data_access.ConnectionParameter;
import data_access.DbUtils;

public class SimpleStudentListProgram {
    public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		System.out.println("=== Print students ===");

		try {
            // Class.forName(ConnectionParameter.jdbcDriver);
            connection = DriverManager.getConnection(ConnectionParameter.connectionString);

        // String insertQuery = "INSERT into student (id, firstname, lastname, postcode, streetaddress, city) values (?, ?, ?, ?, ?, ?)";

        // preparedStatement = connection.prepareStatement(insertQuery);
        // preparedStatement.setInt(1, 2);
        // preparedStatement.setString(2, "quan");
        // preparedStatement.setString(3, "doand");
        // preparedStatement.setString(4, "90548");
        // preparedStatement.setString(5, "pavo havaksentie 4");
        // preparedStatement.setString(6, "Oulu");

        // preparedStatement.executeUpdate();
        // System.out.println("student is added");

        // preparedStatement = null;


        String query = "select * from student";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("postcode"), resultSet.getString("streetaddress"), resultSet.getString("city"));
                System.out.println(student.toString());
            }
            
		} catch (SQLException e) {
			// 7. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + e.getMessage());

		} finally {
			// 8. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuitely(resultSet, preparedStatement, connection);
		}
        
	}
}
