package com.visitor.log.system.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.visitor.log.system.visitor.Visitor;

/**
 * Servlet implementation class CheckInServlet
 */
@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String contactNumber = request.getParameter("contact");
		String address = request.getParameter("address");
		String purposeOfVisit = request.getParameter("purpose");
		String guest_id = request.getParameter("guest_id");
		
//		boolean isCurrentlyInSchool = true;
//		String currentDateAndTime = DateAndTime.currentDateAndTime();
		
	    System.out.println(
	    		"Firstname : " + firstName + "\n" +
	    		"LastName  : " + lastName + "\n" +
	    		"Contact   : " + contactNumber + "\n" +
	    		"Address   : " + address + "\n" +
	    		"Purpose   : " + purposeOfVisit + "\n" +
	    		"Guest_id  : " + guest_id + "\n"
	    );
	    
	    Visitor visitor = new Visitor();
		
	    visitor.setFirstName(firstName);
	    visitor.setLastName(lastName);
	    visitor.setContactNumber(contactNumber);
	    visitor.setAddress(address);
	    visitor.setPurposeOfVisit(purposeOfVisit);
	    visitor.setGuestID(guest_id);
	    
	    request.setAttribute("visitor", visitor);
	    request.getRequestDispatcher("confirm_info.jsp").forward(request, response);
	    
	}

}
