package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;


public class DaoStudent {
	
	public DaoStudent() {
		try {
			Class.forName(ConnectionParameter.jdbcDriver);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameter.driverConnection);
	}
	
	public List<Student> getStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<>();
		
		try {
			connection = getConnection();
			String query = "select * from student";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetAddress");
				String postCode = resultSet.getString("postCode");
				String postOffice = resultSet.getString("postOffice");
				Student student = new Student(id, firstname, lastname, streetAddress, postCode, postOffice);
				list.add(student);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}finally {
			DbUtils.closeUtils(connection, preparedStatement, resultSet);
		}
		return list;
	}
	public Student getStudentById(int id) {
		ResultSet resultSet  = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		Student student = new Student();
		
		try {
			connection = getConnection();
			String query = "select * from student where id = ?" ;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				student.setId(resultSet.getInt("id"));
				
				student.setFirstname(resultSet.getString("firstname"));
				student.setLastname(resultSet.getString("lastname"));
				student.setStreetAddress(resultSet.getString("streetAddress"));
				student.setPostCode(resultSet.getString("postCode"));
				student.setPostOffice(resultSet.getString("postOffice"));
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			DbUtils.closeUtils(connection, preparedStatement, resultSet);
		}
		
		return student;
	}
	
	public int addStudent(Student student) {
		int codeError = -1;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			
			String query = "insert into student (id, firstname, lastname, streetaddress, postcode, postoffice) values (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetAddress());
			preparedStatement.setString(5, student.getPostCode());
			preparedStatement.setString(6, student.getPostOffice());
			
			int result = preparedStatement.executeUpdate();
			if(result != 0) {
				codeError = 1;
			} else {
				codeError = 0;
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			DbUtils.closeUtils(connection, preparedStatement, resultSet);
		}
		return codeError;
	}
	
	public int deleteStudent(int id) {
		int codeError = -1;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(ConnectionParameter.driverConnection);
			String query = "delete from student where id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
		 int result =	preparedStatement.executeUpdate();
			if(result != 0) {
				codeError = 1;
			} else {
				codeError = 0;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			DbUtils.closeUtils(connection, preparedStatement, resultSet);
		}
		return codeError;
	}
}



