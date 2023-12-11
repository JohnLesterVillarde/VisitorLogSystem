package com.visitor.log.system.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.visitor.log.system.utilities.EncryptionAndDecryption;
import com.visitor.log.system.utilities.JDBC;
import com.visitor.log.system.utilities.Verification;

/**
 * Servlet implementation class ChangeUsernameAndPassword
 */
@WebServlet("/ChangeUsernameAndPassword")
public class ChangeUsernameAndPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUsernameAndPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldUsername = request.getParameter("old_username");
		String oldPassword = request.getParameter("old_password");
		String newUsername = request.getParameter("new_username");
		String newPassword = request.getParameter("new_password");
		
		System.out.println(newUsername);
		System.out.println(newPassword);

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sqlStatement = "";
		
		try {
			if(Verification.loginAndPassword(oldUsername, oldPassword)) {
				
				connection = (Connection) JDBC.getDBConnection();
				
				if(newUsername.equals("") && newPassword.equals("")) {
					
					return;
					
				}else if(newUsername.equals("")) {
					
					newPassword = EncryptionAndDecryption.encrypt(newPassword);
					sqlStatement = "UPDATE user SET password = ? WHERE id = 1";
					preparedStatement = connection.prepareStatement(sqlStatement);
					preparedStatement.setString(1, newPassword);
					
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected > 0) {
						request.setAttribute("resultMessage", "Password Updated Succesfully");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}else {
						request.setAttribute("resultMessage", "Failed to update Password");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}
					
				}else if(newPassword.equals("")) {
					
					newUsername = EncryptionAndDecryption.encrypt(newUsername);
					sqlStatement = "UPDATE user SET usercode = ? WHERE id = 1";
					preparedStatement = connection.prepareStatement(sqlStatement);
					preparedStatement.setString(1, newUsername);
					
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected > 0) {
						request.setAttribute("resultMessage", "Username Updated Succesfully");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}else {
						request.setAttribute("resultMessage", "Failed to update Username");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}
					
				}else {
					
					newPassword = EncryptionAndDecryption.encrypt(newPassword);
					newUsername = EncryptionAndDecryption.encrypt(newUsername);
					sqlStatement = "UPDATE user SET password = ?, usercode = ? WHERE id = 1";
					preparedStatement = connection.prepareStatement(sqlStatement);
					preparedStatement.setString(1, newPassword);
					preparedStatement.setString(2, newUsername);
					
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected > 0) {
						request.setAttribute("resultMessage", "Username & Password Updated Succesfully");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}else {
						request.setAttribute("resultMessage", "Failed to update Username & Password");
						request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
					}
					
				}
				
			}else {
				request.setAttribute("UpdateStatus", "Update Failed");
				request.setAttribute("resultMessage", "Invalid Login or Password");
				request.getRequestDispatcher("username_and_password_update_confirmation.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
