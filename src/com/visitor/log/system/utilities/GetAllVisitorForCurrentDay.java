package com.visitor.log.system.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.visitor.Visitor;

public class GetAllVisitorForCurrentDay {

	public static void main(String[] args) throws SQLException  {
		
		LinkedList<Visitor> list = getTodayVisitor();
		
		for(Visitor visitor : list) {
			System.out.println(
				"Firstname    : " + visitor.getFirstName() + "\n" +
				"Lastname     : " + visitor.getLastName() + "\n" +
				"IsActive     : " + visitor.isCurrentlyInSchool() + "\n" +
				"Enter date   : " + visitor.getDateTimeEntered() + "\n" +
				"Exit date    : " + visitor.getDateTimeExited() + "\n"
			);
		}
		System.out.println(getTotalVisitors());
		System.out.println(getActiveVisitors());
		System.out.print(getExitedVisitors());
	}
	
	public static LinkedList<Visitor> getTodayVisitor() {
		
		LinkedList<Visitor> list =  new LinkedList<>();
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT * FROM visitor_log_information WHERE DATE(date_time_enter) = CURDATE();";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				//catching value of date_time_exit if null
				String dateEnter = formatToTime(resultSet.getTimestamp("date_time_enter"));
				Timestamp dateExitFromDB = resultSet.getTimestamp("date_time_exit");
				
				String dateExit = "";
				if (dateExitFromDB == null) {
			        dateExit = "";
			    } else {
			        dateExit = formatToTime(dateExitFromDB);
			    }
				
				System.out.println("enter: " + dateEnter);
				System.out.println("exit: " + dateExit + "\n");
				
				//convert TinyInt into boolean value
				int getTinyIntValue = resultSet.getInt("is_currently_in_school");
				boolean isActive = false;
				
				if(getTinyIntValue == 1) {
					isActive = true;
				}
				
				Visitor visitor = new Visitor();
				
				visitor.setFirstName(resultSet.getString("firstname"));
				visitor.setLastName(resultSet.getString("lastname"));
				visitor.setPurposeOfVisit(resultSet.getString("purpose_of_visit"));
				visitor.setCurrentlyInSchool(resultSet.getBoolean("is_currently_in_school"));
				visitor.setDateTimeEntered(dateEnter);
				visitor.setDateTimeExited(dateExit);
				visitor.setCurrentlyInSchool(isActive);		
				list.add(visitor);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int getTotalVisitors() throws SQLException {
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT COUNT(*) AS total FROM visitor_log_information where date(date_time_enter) = curdate();";
		int totalVisitor = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				totalVisitor = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return totalVisitor;
	}
	
	
	public static int getActiveVisitors() throws SQLException {
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT COUNT(*) AS total FROM visitor_log_information WHERE DATE(date_time_enter) = CURDATE() AND is_currently_in_school = 1;";
		int totalActiveVisitor = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				totalActiveVisitor = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return totalActiveVisitor;
	}
	
	public static int getExitedVisitors() throws SQLException {
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT COUNT(*) AS total FROM visitor_log_information WHERE DATE(date_time_enter) = CURDATE() AND is_currently_in_school = 0;";
		int totalExitedVisitor = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				totalExitedVisitor = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return totalExitedVisitor;
	}
	
	private static String formatToTime(Timestamp timeStamp) {
		LocalDateTime localDateTime = timeStamp.toLocalDateTime();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
	    return localDateTime.format(formatter);
	}


}
