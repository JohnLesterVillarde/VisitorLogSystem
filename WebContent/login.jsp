<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Login Page</title>
  </head>
  <body>
    <header>
      <a href="homepage.jsp"><img src="image/icons8-back-64.png" class="back_icon" alt="back icon" /></a> 
      <p>BCC Visitor Log System</p>
      <img src="image/BCClogo.png" id="logo" alt="bago city college logo" />

    </header>
    
    <% HttpSession jspSession = request.getSession();
    	if(jspSession != null && jspSession.getAttribute("loggedInUser") != null) {
    		response.sendRedirect("admin_page.jsp");
    	}else{
    		
    		String errorMessage = (String) request.getAttribute("LoginEorrorMessage");
    		if(errorMessage != null) {
    			errorMessage = errorMessage;
    		}else {
    			errorMessage = "";
    		}
    %>
    	    
    	     
    	     
    	    <form action="LoginServlet" method="post">
    	      <div class="inputs">
    	  
    	        <label for="usercode">Usercode: </label>
    	        <input type="text" id="usercode" name="usercode" />
    	        <br /><br />
    	        <label for="password">Password: </label>
    	        <input type="password" id="password" name="password"/>
    	        <br /><br />
    	        <input type="submit" value="LOGIN" />
    	        
    	        <p id="errorMessage"><%= errorMessage %></p>
    	      </div>
    	    </form>
    	    
   	<% 
    	}
    %>
  </body>
</html>

