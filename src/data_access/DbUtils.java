package data_access;

import java.sql.*;


public class DbUtils {
    
    public static void closeQuitely(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        closeQuitely(preparedStatement, connection);
        closeQuitely(resultSet);
    }

    public static void closeQuitely(PreparedStatement preparedStatement, Connection connection) {
        closeQuitely(preparedStatement);
        closeQuitely(connection);
    }

    public static void closeQuitely(PreparedStatement preparedStatement) {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            } 
        } catch (SQLException ex) {

        }
    }
    public static void closeQuitely(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            } 
        } catch (SQLException ex) {
            
        }
    }
    public static void closeQuitely(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            } 
        } catch (SQLException ex) {
            
        }
    }
}
