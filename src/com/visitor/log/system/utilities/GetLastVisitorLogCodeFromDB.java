package com.visitor.log.system.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLastVisitorLogCodeFromDB {
	
	public static int getVisitorLogCode(Connection dbCon) {

		String sqlQuery ="SELECT visitor_log_code FROM visitor_log_information ORDER BY visitor_log_code DESC LIMIT 1;";
		
		PreparedStatement preparedStatement;
		int code = 0;
		
		try {
			preparedStatement = dbCon.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			code = resultSet.getInt("visitor_log_code") + 1;
		}else {
			code = DateAndTime.monthAndDay();

		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return code;
		}

}
