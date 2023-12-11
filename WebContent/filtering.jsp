<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@ page import="java.util.LinkedList"%>
<%@ page import="com.visitor.log.system.visitor.Visitor"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Filter Log History</title>

    <style>
     .admin_body {
        height: 100vh;
        width: 100vw;

        overflow: hidden;
      }

      .main_content {
        display: flex;
        width: 100vw;
        height: calc(100% - 8.5rem);

      }

      .side_navigation {
        width: 15%;
        height: 100%;
        background-color: hsl(270, 6%, 20%);
      }

      .side_nav_content {
        color: white;
      }

      .side_nav_content:nth-child(1){
        text-align: center;
        font-size: 1.8rem;
        padding: 2em;
      }

      .side_nav_content:not(:nth-child(1)) {
        padding: 1em;
      }

      .side_nav_content:not(:nth-child(1)):hover {
        color: hsl(224, 62%, 46%);
        background-color: rgba(240, 248, 255, 0.455);
        transform: translateX(1rem);
        transition: 0.3s linear;
      }

        /*filtering.html*/
     .filter_main {
        box-sizing: border-box;
        width: calc(100% - 15%);
        height: 100%;
        padding: 0rem;
        /* overflow-x: hidden; */
        
        display: flex;
        flex-direction: column;
      }

      .select_container {
        background-color: hsl(270, 6%, 20%);
        width: 100%;
        position: relative;
        margin: 0;
      }

      #select_filter {
        color: white;
        min-width: 20rem;
        background-color: transparent;
        border: none;
        padding: 1rem;
        border-radius: 0%;
        font-size: 1.8rem;
      }

      select:focus {
        outline: none;
      }

      select option {
        color: hsl(270, 6%, 20%);
        border-radius: 0px;
        padding: 2rem 1rem;
      }

      .filter_box {
        box-sizing: border-box;
        width: 100%;
        height: 8rem;
        margin: 0px;
        padding-top: 1rem;
        /* border: 3px solid red; */

        position: relative;
      }

      .display_filter {
        /* border: 3px solid green; */

        box-sizing: border-box;
        margin: 0rem;
        width: 100%;
        min-height: 83%;
        position: relative;
        align-self: flex-start;
        background-color: hsl(223, 12%, 89%);
      }

      .filter_input {
        /* border: 3px solid purple; */
        padding: 0rem 1rem;
        margin: 2rem;
        max-width: 60rem;
        max-height: 4rem;
        margin: auto;
        overflow-y: hidden;

       display: flex;
       flex-wrap: nowrap;
       align-items: center;
      }

      .input_for_filter {
        min-height: 3.5rem;
        padding-left: 2rem;
        flex-grow: 1;
        border-radius: 1.5rem 0rem 0rem 1.5rem;
      }

      #lastname_input {
        border-radius: 0px;
        border-left: none;
      }

      .input_for_filter:focus {
        outline: none;
      }

      #submit_filter {
        max-width: 8rem;
        height: 3.5rem;
        padding: 0%;
        padding-top: 0.3rem;
        transform: translateY(-1.25rem);
        border-radius: 0rem 1.5rem 1.5rem 0rem;
        background-color: white;
        color: hsl(224, 62%, 46%);
        font-weight: 600;
      }







      #filter_by_name {
        display: block;
      }

      #filter_by_date {
        display: none;
      } 

      .filter_content {
        /* border: 3px solid blue; */
        max-height: 86.5%;
        margin-top: 1rem;
        box-sizing: border-box;
        overflow-y: auto;
        overflow-x: hidden;
      }

      .filter_content::-webkit-scrollbar {
        width: 0.3rem;
      }


      .filter_table_header {
        width: 100%;
        box-sizing: border-box;
        padding: 1rem 0rem;

        display: grid;
        grid-template-columns: 3% 20% 11% 11% 11% 6% 20% 20%;
      }

      .header_label {
        text-align: center;
        font-weight: bold;
        font-size: 1.5rem;
      }

      .filter_content {
        width: 100%;
        box-sizing: border-box;
      }

      .result_row {
        box-sizing: border-box;
        width: 100%;
        min-height: 5rem;
        display: grid;
        grid-template-columns: 3% 20% 11% 11% 11% 6% 20% 20%;
        border-bottom: 1px solid black;
        padding-top: 0.8rem;
        font-size: 1.5rem;
      }

      .result_row:hover {
        background-color: hsl(228, 33%, 97%);
        transition: 0.3s;
      }

      .filter_result {
        text-align: center;
        text-overflow: ellipsis;
      }
      
      .contain_logout_button {
        margin: auto;
        position:relative;
        bottom: -47%;
        right: 7%;
        padding: 1rem;
      }

      #log_out_button {
        width: 100%;
        background-color: transparent;
        border: none;
        font-size: clamp(1.3rem, 3vw, 1.7rem);
        font-weight: bold;
      }
      
    </style>
</head>
<body class="admin_body">

    <header>
        <a href="homepage.jsp"
          ><img src="image/icons8-back-64.png" class="back_icon" alt="back icon"
        /></a>
        <p>BCC Visitor Log System</p>
        <img src="image/BCClogo.png" id="logo" alt="" bago city college logo />
     </header> 

     <div class="main_content">

        <nav class="side_navigation">
          <div class="side_nav_content">ADMIN</div>
          <div class="side_nav_content" ><a href="admin_page.jsp">Dashboard</a></div>
          <div class="side_nav_content" id="active_page">Filter Log History</div>
          <div class="side_nav_content"><a href="change_username_password.jsp"> Manage Username & Password </a></div>
          
          <form action="LogOutServlet" method="post" class="contain_logout_button">
            <input type="submit" value="LOGOUT" id="log_out_button">
          </form>
        </nav>

        <main class="filter_main">

          <div class="select_container">
                <select name="select_filter" id="select_filter" onchange="toggleInput">
                    <option value="date">Filter by Name</option>
                    <option value="name">Filter by Date</option>
                </select>
          </div>

          <div class="filter_box">
            <div id="filter_by_name">
              <form class="filter_input"  action="SearchByNameServlet" method="post">
                <input type="text" name="firstName" class="input_for_filter" placeholder="Firstname">
                <input type="text" name="lastName" class="input_for_filter" id="lastname_input" placeholder="Lastname">
                <input type="submit" id="submit_filter" value="Search">
              </form> 
            </div>   

            <div id="filter_by_date">
              <form class="filter_input"  action="SearchByDateServlet" method="post">
                <input type="date" name="date" class="input_for_filter" required>
                <input type="submit" id="submit_filter" value="Search">
              </form> 
            </div>   
          </div>
          
          <div class="display_filter"> 
            <hr>

            <div class="filter_table_header">
              <div class="header_label"></div>
              <div class="header_label"> Name</div>
              <div class="header_label"> Contact</div>
              <div class="header_label"> Address</div>
              <div class="header_label"> Purpose</div>
              <div class="header_label"> Guest ID</div>
              <div class="header_label"> IN</div>
              <div class="header_label"> OUT</div>
            </div>
            <hr>
            
            <div class="filter_content">
            <% 
            
            int counter = 1;
            	
            Object storeAttribute = session.getAttribute("visitorList");
            if(storeAttribute instanceof LinkedList) {
            	LinkedList<Visitor> visitorList = (LinkedList<Visitor>) storeAttribute;
            		
            	if(visitorList ==null && visitorList.isEmpty()) {
            		out.println("<p>" + "No Results Found " + "<p>");
            	}
            	
	            for(Visitor visitor : visitorList) {
	        %>    		
	        	
              		<div class="result_row">
                		<div class="filter_result"> <%= counter++ %></div>
                		<div class="filter_result"> <%= visitor.getFirstName() +" "+ visitor.getLastName() %></div>
                		<div class="filter_result"> <%= visitor.getContactNumber() %></div>
                		<div class="filter_result"> <%= visitor.getAddress() %></div>
                		<div class="filter_result"> <%= visitor.getPurposeOfVisit() %></div>
                		<div class="filter_result"> <%= visitor.getGuestID() %></div>
                		<div class="filter_result"> <%= visitor.getDateTimeEntered() %></div>
                		<div class="filter_result"> <%= visitor.getDateTimeExited() %></div>
              		</div>    
	            	
	        <%			
	            }
            }
            %>
            </div>

          </div>  
       </main>   
     </div>

     <script>

        let selectValue = document.getElementById("select_filter");
        let filterByName = document.getElementById("filter_by_name");
        let filterByDate = document.getElementById("filter_by_date");

        selectValue.addEventListener('change', (event) => {
          if (event.target.value == "date") {
            filterByDate.style.display = 'none';
            filterByName.style.display = 'block';

          }else if (event.target.value == "name") {
            filterByName.style.display = 'none';
            filterByDate.style.display = 'block';

          }
        })


     </script>

</body>
</html>