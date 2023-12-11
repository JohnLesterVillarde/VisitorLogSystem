package com.visitor.log.system.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.utilities.JDBC;
import com.visitor.log.system.visitor.Visitor;

/**
 * Servlet implementation class SearchByDateServlet
 */
@WebServlet("/SearchByDateServlet")
public class SearchByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inputDate = request.getParameter("date");
		System.out.println("Input Date: "+ inputDate);
		LinkedList<Visitor> visitorList = new LinkedList<>();
		int counter = 1;
		
		Connection connection = (Connection) JDBC.getDBConnection();
		String sql = "SELECT * FROM visitor_log_information WHERE DATE(date_time_enter) = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, inputDate);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String firstNameFromDB = rs.getString("firstname");
				String lastNameFromDB = rs.getString("lastname");
				String contact = rs.getString("contact_number");
				String address = rs.getString("address");
				String purpose = rs.getString("purpose_of_visit");
				String guestID = rs.getString("guest_id");
				String dateTimeEnter = formatDateTime(rs.getTimestamp("date_time_enter"));
				String dateTimeExit = formatDateTime(rs.getTimestamp("date_time_exit"));
					
				System.out.println(
						counter++ +"\n" +
						"Firstname    : " + firstNameFromDB + "\n" +
					    "LastName     : " + lastNameFromDB + "\n" +
					    "Contact      : " + contact + "\n" +
					    "Address      : " + address + "\n" +
					    "Purpose      : " + purpose + "\n" +
					    "Guest_id     : " + guestID + "\n"+
					    "Date Enter   : " +  dateTimeEnter + "\n" +
					    "Date Enter   : " +  dateTimeExit + "\n"
				);
				
				Visitor visitor = new Visitor();
				visitor.setFirstName(firstNameFromDB);
				visitor.setLastName(lastNameFromDB);
				visitor.setContactNumber(contact);
				visitor.setAddress(address);
				visitor.setPurposeOfVisit(purpose);
				visitor.setGuestID(guestID);
				visitor.setDateTimeEntered(dateTimeEnter);
				visitor.setDateTimeExited(dateTimeExit);
				
				visitorList.push(visitor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("visitorList", visitorList);
		
		request.getRequestDispatcher("filtering.jsp").forward(request, response);
	}
	
	private static String formatDateTime(Timestamp timeStamp) {
		if(timeStamp == null) {
			return "";
		}
			LocalDateTime localDateTime = timeStamp.toLocalDateTime();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:a");
		    return localDateTime.format(formatter);
	}

}
