<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Visitor check-in page</title>
  </head>
  <body>
  	<div id="preloader"></div>
    <header>
      <a href="homepage.jsp"><img src="image/icons8-back-64.png" class="back_icon" alt="back icon" /></a> 
      <p>BCC Visitor Log System</p>
      <img src="image/BCClogo.png" id="logo" alt="" bago city college logo />
    </header>
    <nav class="nav">
      <a href="check_in.jsp"
        ><div class="navigation" id="active_page">Check-in</div></a
      >
      <a href="check_out.jsp"><div class="navigation">Check-out</div></a>
      <a href="daily_visitor_logs.jsp"
        ><div class="navigation">Daily Visitor Logs</div></a
      >
    </nav>

    <hr />

    <main>
      <form action="CheckInServlet" method="post">

        <label for="firstname" class="label">Firstname: </label><input type="text" id="firstname" name="firstname" required>
        <label for="lastname" class="label">Lastname: </label><input type="text" id="lastname" name="lastname" required>
        <label for="contact" class="label">Contact number: </label><input type="number" id="contact" name="contact">
        <label for="address" class="label">Address: </label><input type="text" id="address" name="address">
        <label for="purpose" class="label">Purpose of Visit: </label><input type="text" id="purpose" name="purpose">
        <label for="guest_id" class="label">Guest ID: </label><input type="number" id="guest_id" name="guest_id" required>
        <input type="reset" id="reset" value="RESET">
        <input type="submit" value="ENTER">
      </form>
    </main>
   	
   	<script src="Loading.js"></script>
  </body>
</html>
