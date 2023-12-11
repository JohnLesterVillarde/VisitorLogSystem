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
import com.visitor.log.system.utilities.JDBC;

/**
 * Servlet implementation class UpdateVisitorExitLogServlet
 */
@WebServlet("/UpdateVisitorExitLogServlet")
public class UpdateVisitorExitLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVisitorExitLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputGuestID = request.getParameter("inputGuestID");
		String guestID = request.getParameter("guestID");
		String stringVisitorLogCode = request.getParameter("visitorCode");
		String timeLogExit = DateAndTime.currentDateAndTime();
		int intVisitorLogCode = 0;
		try {
			intVisitorLogCode = Integer.parseInt(stringVisitorLogCode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("input; " + inputGuestID);
		System.out.println("guestID " + guestID);
		System.out.println("visitore code : " + stringVisitorLogCode);
		System.out.println("exit time : " + timeLogExit);
		
		if(inputGuestID.contentEquals(guestID)) {
			System.out.println("guest id matched");
			Connection connection = (Connection) JDBC.getDBConnection();
			String sqlQuery = "UPDATE visitor_log_information SET is_currently_in_school = false, date_time_exit = ? WHERE visitor_log_code = ?";
			
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setString(1, timeLogExit);
				preparedStatement.setInt(2, intVisitorLogCode);
				
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println("rows affected = " + rowsAffected);
				if(rowsAffected > 0) {
					response.sendRedirect("check_out.jsp");
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("guest id dont matched");
			response.sendRedirect("check_out.jsp");
		}
	}

}
