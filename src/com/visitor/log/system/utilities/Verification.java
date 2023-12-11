package com.visitor.log.system.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;

import com.visitor.log.system.utilities.EncryptionAndDecryption;
import com.visitor.log.system.utilities.JDBC;

public class Verification {
	
	public static boolean loginAndPassword(String usercode, String password) throws Exception {
		
		boolean isValidUsercodeAndPassword = false;
		String encryptUsercode = EncryptionAndDecryption.encrypt(usercode);
		
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT * FROM user WHERE usercode = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, encryptUsercode);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		System.out.println("connection out");

		while(resultSet.next()) {
			System.out.println("connection in");
			
			String passwordFromDB = resultSet.getString("password");
			String usercodeFromDB = resultSet.getString("usercode");
			
			System.out.println("password: "+ passwordFromDB);
			System.out.println("usercode: " + usercodeFromDB);
			
			String decryptedPassword = EncryptionAndDecryption.decrypt(passwordFromDB, EncryptionAndDecryption.secretKey);
			String decryptedUsercode = EncryptionAndDecryption.decrypt(usercodeFromDB, EncryptionAndDecryption.secretKey);
			
			if(password.equals(decryptedPassword) && usercode.equals(decryptedUsercode)) {
				isValidUsercodeAndPassword = true;
			}
		}
		
		return isValidUsercodeAndPassword;	
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println(loginAndPassword("admin", "1234"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
