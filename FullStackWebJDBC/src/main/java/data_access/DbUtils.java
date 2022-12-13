package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils {
	public static void closeUtils(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeUtils(connection);
		closeUtils(preparedStatement, resultSet);
	}
	public static void closeUtils(Connection connection) {
		try {
			if(connection != null)
			{
				connection.close();			
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void closeUtils( PreparedStatement preparedStatement, ResultSet resultSet) {
		closeUtils(preparedStatement);
		closeUtils(resultSet);
	}
	public static void closeUtils(PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
		System.out.println(ex.getMessage());
		}
	}
	
	public static void closeUtils(ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
		System.out.println(ex.getMessage());
		}
	}
	
	
}
