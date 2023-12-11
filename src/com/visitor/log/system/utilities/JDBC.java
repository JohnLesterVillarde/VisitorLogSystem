package com.visitor.log.system.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/visitor_log_system"; //database name
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "1234";

	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
			System.out.println("JDBC is now connected");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
//}
//END OF DATABASE CONNECTION

//this will test the connection if it connects to the database and inserts data
    public static void main(String[] args) {

        try {
// Step 1: Register the MySQL JDBC driver (optional for Java 6 and later)
            Class.forName(DB_DRIVER);
            System.out.println("JDBC CONNECTED!");
        
// Step 2: Create a connection
            Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

// If the connection is successful, you will reach this point
            System.out.println("Connected to MySQL database!");
        
// Step 2: Prepare the SQL query
            PreparedStatement preparedStatement = null;
			String insertQuery = "INSERT INTO user (id, usercode, password) VALUES (?, ?, ?)";
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertQuery);
				
// Step 3: Set the values for the parameters
			String id = "1";
			String username = EncryptionAndDecryption.encrypt("admin");
			String password = EncryptionAndDecryption.encrypt("1234");
				
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);

// Step 4: Execute the query
			int rowsAffected = preparedStatement.executeUpdate();
				
			if (rowsAffected > 0) {
				System.out.println("Entry added to the database successfully!");
			} else {
				System.out.println("Failed to add entry to the database.");
			}
// Step 3: Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

