package com.visitor.log.system.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.utilities.DateAndTime;
import com.visitor.log.system.utilities.GetLastVisitorLogCodeFromDB;
import com.visitor.log.system.utilities.JDBC;

/**
 * Servlet implementation class InsertDataToDatabase
 */
@WebServlet("/InsertDataToDatabase")
public class InsertDataToDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDataToDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) JDBC.getDBConnection();

		int visitorLogCode = GetLastVisitorLogCodeFromDB.getVisitorLogCode(connection);
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String contactNumber = request.getParameter("contact");
		String address = request.getParameter("address");
		String purposeOfVisit = request.getParameter("purpose");
		String guest_id = request.getParameter("guest_id");
		boolean isCurrentlyInSchool = true;
		String dateTimeEntered = DateAndTime.currentDateAndTime();
		
	    System.out.println(
	    		"Visitor code : " + visitorLogCode + "\n"+
	    		"Firstname    : " + firstName + "\n" +
	    		"LastName     : " + lastName + "\n" +
	    		"Contact      : " + contactNumber + "\n" +
	    		"Address      : " + address + "\n" +
	    		"Purpose      : " + purposeOfVisit + "\n" +
	    		"Guest_id     : " + guest_id + "\n"+
	    		"is active    : " + isCurrentlyInSchool + "\n" +
	    		"Date Enter   : " +  dateTimeEntered
	    );
	    
	    String sqlQuery = "INSERT INTO visitor_log_information (visitor_log_code, firstname, lastname, contact_number, address, purpose_of_visit, guest_id, is_currently_in_school, date_time_enter) VALUES (?,?,?,?,?,?,?,?,?)";
	    try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, visitorLogCode);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, contactNumber);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, purposeOfVisit);
			preparedStatement.setString(7, guest_id);
			preparedStatement.setBoolean(8, isCurrentlyInSchool);
			preparedStatement.setString(9, dateTimeEntered);
			
			preparedStatement.executeUpdate();
			
			response.sendRedirect("check_in.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

		
		
	}
	
	
	
	

}
