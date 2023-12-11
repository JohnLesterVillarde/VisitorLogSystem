package com.visitor.log.system.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.utilities.GetAllActiveVisitorFromDatabase;
import com.visitor.log.system.utilities.JDBC;
import com.visitor.log.system.visitor.Visitor;

/**
 * Servlet implementation class ToGetActiveVisitorsServlet
 */
@WebServlet("/ToGetActiveVisitorsServlet")
public class ToGetActiveVisitorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToGetActiveVisitorsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection connection = (Connection) JDBC.getDBConnection();	
		LinkedList<Visitor> listOfActiveVisitors = (LinkedList<Visitor>) GetAllActiveVisitorFromDatabase.getActiveVisitors(connection);
		
		 request.setAttribute("listOfActiveVisitors", listOfActiveVisitors);
		 request.getRequestDispatcher("check_out.jsp").forward(request, response);
		
	}
	
	public static void main(String[] args) {
		
		Connection connection = (Connection) JDBC.getDBConnection();	
		LinkedList<Visitor> listOfActiveVisitors = (LinkedList<Visitor>) GetAllActiveVisitorFromDatabase.getActiveVisitors(connection);
		
		for(Visitor visitor : listOfActiveVisitors) {
			System.out.println(visitor.getFirstName());
			System.out.println(visitor.getLastName());
			System.out.println(visitor.getGuestID());
			System.out.println("");
		}
		
	}

}
