package com.visitor.log.system.utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.visitor.Visitor;

public class GetAllActiveVisitorFromDatabase {
	
	public static LinkedList<Visitor> getActiveVisitors(java.sql.Connection dbCon) {
		
		LinkedList<Visitor> list = new LinkedList<>();
		String sqlQuery = "SELECT visitor_log_code, firstname, lastname, purpose_of_visit, guest_id, is_currently_in_school FROM visitor_log_information WHERE is_currently_in_school = 1";
		try {
			PreparedStatement prepareStatement = dbCon.prepareStatement(sqlQuery);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				Visitor visitor = new Visitor();
				visitor.setVisitorCode(resultSet.getInt("visitor_log_code"));
				visitor.setFirstName(resultSet.getString("firstname"));
				visitor.setLastName(resultSet.getString("lastname"));
				visitor.setPurposeOfVisit(resultSet.getString("purpose_of_visit"));
				visitor.setGuestID(resultSet.getString("guest_id"));
				int getTinyIntValue = resultSet.getInt("is_currently_in_school");
				boolean isActive = false;
				
				if(getTinyIntValue == 1) {
					isActive = true;
				}else {
					isActive = false;
				}
				visitor.setCurrentlyInSchool(isActive);
				list.add(visitor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public static void main(String[] args) {
		Connection connection = (Connection) JDBC.getDBConnection();
		
		LinkedList<Visitor> list = getActiveVisitors(connection);
		
		for(Visitor visitor : list) {
			System.out.println(
				"Visitor code : " + visitor.getVisitorCode() + "\n"+
				"Firstname    : " + visitor.getFirstName() + "\n" +
				"Lastname     : " + visitor.getLastName() + "\n" +
				"Purpose      : " + visitor.getPurposeOfVisit() + "\n" +
				"Guest id     : " + visitor.getGuestID() + "\n" +
				"IsActive     : " + visitor.isCurrentlyInSchool() + "\n"
			);
		}
	}
	
}
