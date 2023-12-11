package com.visitor.log.system.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import com.visitor.log.system.utilities.EncryptionAndDecryption;
import com.visitor.log.system.utilities.JDBC;
import com.visitor.log.system.utilities.Verification;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
				
		System.out.println("login servlet");
		System.out.println(usercode);
		System.out.println(password);
		
		HttpSession session = request.getSession();
		
		try {
			if(Verification.loginAndPassword(usercode, password)) {
				session.setAttribute("loggedInUser", usercode);
				System.out.println("user loginsuccesfully");
				response.sendRedirect("admin_page.jsp");
			}else {
				System.out.println("invalid information");
				request.setAttribute("LoginEorrorMessage", "Invalid Username or Password. Please try again!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

}
