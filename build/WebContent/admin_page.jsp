<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.visitor.log.system.utilities.GetAllVisitorForCurrentDay"%>
<%@ page import="com.visitor.log.system.utilities.GetNumberOfVisitor" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">

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
      
      .admin_visual_page {
        box-sizing: border-box;
        width: calc(100% - 15%);
        height: 100%;
        padding: 5% 15%;
        overflow-x: auto;
        background-image: url(image/IMG20231118103812.jpg);
        background-position: center;
        background-size: cover;

        display: flex;
        flex-wrap: wrap;
        gap: 3rem;
      }

      .admin_visual_page::-webkit-scrollbar {
        width:  1rem;
      }

      #todays_visitor {
        width: 100%;
        max-height: 30rem;
        min-height: 21rem;
        box-sizing: border-box;
        border: 1px solid white;
        border-radius: 1rem;
        box-shadow: 0 0 0.5rem;
        padding: 2rem;
        backdrop-filter: blur(10px);

        display: grid;
        grid-template-columns: 50% 30%;
        grid-template-rows: 10%fr 1fr 1fr;
        gap: 1.5rem;
      }

      .insight_header {
        /* background-color: hsl(224, 62%, 46%); */
        font-size: clamp(2rem, 2vw, 2rem);
        font-weight: 600;
        backdrop-filter: blur(20px);
        grid-row: 1/2;
        grid-column: 1/4;
        color: white;
      }

      #today_number_of_visitor {
        background-color: hsl(224, 62%, 46%);
        border-radius: 0.7rem 0rem 0rem 7px;
        grid-row: 2/4;
        padding: 2rem;
        color: white;
        text-align: center;
        position: relative;
      }

      #today_number_of_visitor b {
        font-size: clamp(2rem, 7vw, 8rem);
        position: absolute;
        transform: translateX(-50%);
      }

      #active_view {
        background-color: green;
        border-top-right-radius: 7px;
        grid-row: 2/3;
        grid-column: 2/4;

        padding: 1rem;
        color: white;

        display: flex;
        align-items: center;
      }

      #active_view b,
      #inactive_view b {
        font-size: clamp(2rem, 3vw, 6rem);
        margin: 1rem;
      }

      #active_view p,
      #inactive_view p {
       transform: translateY(50%);
      }

      #inactive_view {
        background-color: red;
        border-bottom-right-radius: 7px;
        grid-row: 3/4;
        grid-column: 2/4;

        padding: 1rem;
        color: white;

        display: flex;
        align-items: center;
      }

      #total_visit_for_this_month {
        width: 100%;
        /* min-height: 15rem; */
        height: auto;
        flex: 1 0 25rem;
        box-sizing: border-box;
        border: 1px solid white;
        border-radius: 1rem;
        box-shadow: 0 0 0.5rem;
        backdrop-filter: blur(20px);
        padding: 1rem 2rem;
        padding-bottom: 1.8rem;
      }

      #total_visit_for_last_seven_days {
        width: 100%;
        /* min-height: 15rem; */
        flex: 1 0 25rem;
        box-sizing: border-box;
        border: 1px solid white;
        border-radius: 1rem;
        box-shadow: 0 0 0.5rem;
        backdrop-filter: blur(20px);
        padding: 1rem 2rem;
        padding-bottom: 1.8rem;
      }

      .month_insights {
        width: calc(100% - 2rem);
        height: calc(100 - 2rem);
        background-color: deepskyblue;
        margin-top: 1rem;
        border-radius: 0.7rem;
        padding: 1rem;
        position: relative;
      }

      .week_insights {
        width: calc(100% - 2rem);
        height: calc(100 - 2rem);
        background-color: lightseagreen ;
        margin-top: 1rem;
        border-radius: 0.7rem;
        padding: 1rem;

      }

      .month_insights b,
      .week_insights b {
        font-size: clamp(2rem, 2vw, 4rem);
        margin-top: 4rem;
        margin: 1rem auto;
      }

      .month_insights p,
      .week_insights p {
        display: inline-block;
      }

      #todays_visitor:hover,
      #total_visit_for_this_month:hover,
      #total_visit_for_last_seven_days:hover {
        transform: scale(105%);
        transition: 0.2s ease-in-out;
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
    <title>Admin page</title>
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
          <div class="side_nav_content" id="active_page">Dashboard</div>
          <div class="side_nav_content"><a href="filtering.jsp">Filter Log History</a></div>
          <div class="side_nav_content"><a href="change_username_password.jsp"> Manage Username & Password </a></div>
          
          <form action="LogOutServlet" method="post" class="contain_logout_button">
            <input type="submit" value="LOGOUT" id="log_out_button">
          </form>
        </nav>

        <main class="admin_visual_page">

          <div id="todays_visitor">
            <div class="insight_header">Today's Visitor Logs</div>
            <div id="today_number_of_visitor">
              <p>Currently Logged Visitors:</p>
              <b><%= GetAllVisitorForCurrentDay.getTotalVisitors() %></b>
            </div>
            <div id="active_view">
              <b><%= GetAllVisitorForCurrentDay.getActiveVisitors() %></b>
              <p>Visitor still inside</p>
            </div>

            <div id="inactive_view">
              <b><%= GetAllVisitorForCurrentDay.getExitedVisitors() %></b>
              <p>Visitor exited</p>
            </div>
          </div>

          <div id="total_visit_for_this_month">
            <div class="month_insights">
              <div class="insight_header">This Month's Total Visitor</div>

              <b><%= GetNumberOfVisitor.monthTotal() %></b>
              <p>visitors</p>
            </div>
          </div>

          <div id="total_visit_for_last_seven_days">
            <div class="week_insights">
              <div class="insight_header">Visitor Logs for Last 7 days</div>

              <b><%= GetNumberOfVisitor.visitorOfLastSevenDays() %></b>
              <p>Visitors</p>
            </div>
          </div>

          <!-- <script>
            let currentDateElement = document.getElementById("today_number_of_visitor");

            let currentDate = new Date();
            let options =  {month: 'long', day:'numeric'}
            let formatDate = currentDate.toLocaleString(options);
            currentDateElement.textContent = formatDate;
          </script> -->
        </main>
     </div>

     
    
</body>
</html>