<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="com.visitor.log.system.visitor.Visitor"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Visitor check-in page</title>
  </head>
  <body>
    <header>
      <a href="check_in.jsp"><img src="image/icons8-back-64.png" class="back_icon" alt="back icon" /></a> 
      <p>BCC Visitor Log System</p>
      <img src="image/BCClogo.png" id="logo" alt="" bago city college logo />
    </header>
    <hr />
    
    <%
    	Visitor visitor = (Visitor) request.getAttribute("visitor");
    	String firstName = visitor.getFirstName();
    	String lastName = visitor.getLastName();
    	String contact = visitor.getContactNumber();
    	String address = visitor.getAddress();
    	String purpose = visitor.getPurposeOfVisit();
    	String guest_id = visitor.getGuestID();
    %>
    <main class="modal_background">
        <div class="modal">
            <div class="note">Confirm Information below</div>
            <p class="info_label">Firstname: </p> <p class="info"><%= firstName %></p>
            <p class="info_label">Lastname: </p> <p class="info"><%= lastName %></p>
            <p class="info_label">Contact Number: </p> <p class="info"><%= contact %></p>
            <p class="info_label">Address: </p> <p class="info"> <%= address %></p>
            <p class="info_label">Purpose of visit: </p> <p class="info"><%= purpose %></p>
            <p class="info_label">Guest ID Number: </p> <p class="info"><%= guest_id %></p>

            <div></div>
            <form action="InsertDataToDatabase" method="post">
                <input type="hidden" value="<%= firstName %>" name="firstname">
                <input type="hidden" value="<%= lastName %>" name="lastname">
                <input type="hidden" value="<%= contact %>" name="contact">
                <input type="hidden" value="<%= address %>" name="address">
                <input type="hidden" value="<%= purpose %>" name="purpose">
                <input type="hidden" value="<%= guest_id %>" name="guest_id">
                <input type="submit" class="confirm_button" value="CONFIRM">
            </form>
        </div>
    </main>
  </body>
</html>
