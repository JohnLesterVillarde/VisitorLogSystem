<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.visitor.log.system.utilities.GetAllVisitorForCurrentDay"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="com.visitor.log.system.visitor.Visitor" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
     <link rel="stylesheet" href="style.css" />
    <title>Visitor check-out page</title>
  </head>
  <body>
    <header>
      <a href="homepage.jsp"><img src="image/icons8-back-64.png" class="back_icon" alt="back icon"/></a>
      <p>BCC Visitor Log System</p>
      <img src="image/BCClogo.png" id="logo" alt="" bago city college logo />
    </header>
    <nav class="nav">
      <a href="check_in.jsp"><div class="navigation">Check-in</div></a>
      <a href="check_out.jsp"
        ><div Check-out class="navigation">Check-out</div></a
      >
      <a href="daily_visitor_logs.jsp"
        ><div class="navigation" id="active_page">Daily Visitor Logs</div></a
      >
    </nav>

    <hr />

    <main class="daily_visit_page_body">

      <div class="visit_log_insights">
        <div class="insight" id="total_log">
          <p>Total visitor </p>
          <h2> <%= GetAllVisitorForCurrentDay.getTotalVisitors() %></h2>
        </div>

        <div class="insight" id="total_active">
          <p>Visitor currently inside </p>
          <h2> <%= GetAllVisitorForCurrentDay.getActiveVisitors() %></h2>
        </div>

        <div class="insight" id="total_exit">
          <p>Exited visitor </p>
          <h2> <%= GetAllVisitorForCurrentDay.getExitedVisitors() %></h2>
        </div>
      </div>

      <div id="visit_log_information">
        <div class="visit_log_header">
          <div id="number"></div>
          <div id="firstnam">Name</div>
          <div id="lastname">Purpose of Visit</div>
          <div id="isActive">Active /InActive</div>
          <div id="in">IN</div>
          <div id="out">OUT</div>
        </div>
          
        <div id="visitor_log_box">
        
        <% 
        	LinkedList<Visitor> list = GetAllVisitorForCurrentDay.getTodayVisitor();
        	int num = 1;
        	String id = "";
        	
        if(list != null) {
        	for(Visitor visitor : list) {
        		
        		if(visitor.isCurrentlyInSchool() == true) {
        			id = "Active";
        		}else {
        			id = "In-Active";
        		}
        %>
        
        <div class="visitor_info_box">
            <div class="visitor_info" id="number_log"> <%= num++ %> </div>
            <div class="visitor_info"> <%= visitor.getFirstName() + "  " + visitor.getLastName() %></div>
            <div class="visitor_info"> <%= visitor.getPurposeOfVisit() %></div>
            <div class="visitor_info" id="<%= visitor.isCurrentlyInSchool()%>"> <%= id %></div>
            <div class="visitor_info"> <%= visitor.getDateTimeEntered() %></div>
            <div class="visitor_info"> <%= visitor.getDateTimeExited() %></div>
          </div> 
          		
        <% 		
        	}
        }
        %>
        
            
       </div>  
     </div>
    </main>

  </body>
</html>
