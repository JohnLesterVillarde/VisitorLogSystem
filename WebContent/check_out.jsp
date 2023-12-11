<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.visitor.log.system.visitor.Visitor"%>
<%@ page import="com.visitor.log.system.utilities.GetAllActiveVisitorFromDatabase"%>

<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.ClassCastException"%>

<%@ page import="com.visitor.log.system.utilities.JDBC"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.mysql.jdbc.Connection"%>
<%@ page import="com.visitor.log.system.visitor.Visitor"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Visitor log page</title>
  </head>
  <body class="body">
    <header>
      <a href="homepage.jsp"><img src="image/icons8-back-64.png" class="back_icon" alt="back icon" /></a>
      <p>BCC Visitor Log System</p>
      <img src="image/BCClogo.png" id="logo" alt="" bago city college logo />
    </header>
    <nav class="nav">
      <a href="check_in.jsp"><div class="navigation">Check-in</div></a>
      <a href="check_out.jsp"
        ><div class="navigation" id="active_page">Check-out</div></a
      >
      <a href="daily_visitor_logs.jsp"
        ><div class="navigation">Daily Visitor Logs</div></a
      >
    </nav>

    <hr />

    <main class="check_out_visitor_box">
    
    <div class="table_header">
        <div>Firstname</div>
        <div>Lastname</div>
        <div>Guest ID Number</div>
        <div></div>
    </div>
    <div class="check_out_form_box">
      
    <% 
    	//@SuppressWarnings("unchecked")
    LinkedList<Visitor> listOfActiveVisitors = null;
	Connection connection = (Connection) JDBC.getDBConnection();
	int num = 0;
    	try {
			listOfActiveVisitors = GetAllActiveVisitorFromDatabase.getActiveVisitors(connection);
    	}catch (ClassCastException e) {
    		
    	}
	      if (listOfActiveVisitors != null) {
	          for (Visitor visitor : listOfActiveVisitors) {
	              int visitorCode = visitor.getVisitorCode();
	              String firstName = visitor.getFirstName();
	              String lastName = visitor.getLastName();
	              String guestID = visitor.getGuestID();
	              boolean isActive = visitor.isCurrentlyInSchool();
	              num++;
	              
      %>  
        
      <form class="check_out_form" action="UpdateVisitorExitLogServlet" method="post" onsubmit="return validateForm()">
        <div class="form_item">	 <%= firstName %></div>
        <div class="form_item"><%= lastName %></div>
        <div class="form_item"><input type="number" class="inputCode" name="inputGuestID" placeholder="000" />
        					   <input type="hidden" name="visitorCode" value="<%= visitorCode %>" />
        					   <input type="hidden" name="guestID" class="code" value="<%= guestID%>" /> </div>
        <div class="form_item"><input id="check-out_submit" type="submit" value="EXIT"></div> 
      </form>
      
      <script>
      
      function validateForm(form) {
          let input = form.querySelector('.inputCode');
          let code = form.querySelector('.code');

          let inputValue = input.value;
          let codeValue = code.value;

          if (inputValue === codeValue) {
              // Reset styles if values match
              input.style.color = ""; // Reset to default color
              input.style.borderColor = ""; // Reset to default border color
              input.style.outline = ""; // Reset to default outline
              return true; // Return true if values match
          } else {
			  //alert("Incorrect GuestID");
              input.value = "";
              input.style.color = "red";
              input.style.borderColor = "red";
              input.style.borderWidth = "3px";
              input.style.outline = "red";
              return false; // Return false if values do not match
          }
      }

      document.addEventListener('DOMContentLoaded', function () {
          let forms = document.querySelectorAll('.check_out_form');

          forms.forEach(function (form) {
              form.addEventListener('submit', function (event) {
                  if (!validateForm(form)) {
                      event.preventDefault(); // Prevent form submission if validation fails
                  }
              });
          });
      });
 

      
      
      /*
      let isValid = false;
      var input = document.getElementById("inputCode").value;
      var code = document.getElementById("code").value;
      console.log('code ' + code)
      console.log('input_value ' + code)
      alert(code + " " + input_value)
      function validateForm() {
        if(input ==  code) {
          isValid =  true;
        }else {
          alert("Incorrect GuestID");
          input.value = "000";
          input.style.color = "red";
          //input.style.borderColor = "red";
          input.style.outline = "red";
          isValid = false;
        }
        return isValid;
      }*/

    </script>
              
    <%
	          }
	     }
    %>
        </div>     
    </main>
  </body>
</html>
