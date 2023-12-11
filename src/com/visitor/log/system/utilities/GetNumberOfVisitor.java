package com.visitor.log.system.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;

import com.mysql.jdbc.Connection;

public class GetNumberOfVisitor {

	public static void main(String[] args) {
		System.out.println(monthTotal());
		System.out.println(visitorOfLastSevenDays());


	}
	
	public static int monthTotal() {
		
		int currentlyVisitorOfThisMonth = 0;
		YearMonth yearMonth = YearMonth.now();
		
		try {
			Connection connection = (Connection) JDBC.getDBConnection();
			String sql = "SELECT COUNT(*) AS monthLog FROM visitor_log_information WHERE YEAR(date_time_enter) = ? AND MONTH(date_time_enter) = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setInt(1, yearMonth.getYear());
			preparedStatement.setInt(2, yearMonth.getMonthValue());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				currentlyVisitorOfThisMonth = rs.getInt("monthLog");
			}
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentlyVisitorOfThisMonth;
	}
	
	public static int visitorOfLastSevenDays() {
		
		int visitorOfLastSevenDays = 0;
		
		try {
			Connection connection = (Connection) JDBC.getDBConnection();
			String sql = "SELECT COUNT(*) AS total FROM visitor_log_information WHERE date_time_enter >= CURRENT_DATE - INTERVAL 7 DAY;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				visitorOfLastSevenDays = rs.getInt("total");
			}
			
			connection.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return visitorOfLastSevenDays;
	}

}
