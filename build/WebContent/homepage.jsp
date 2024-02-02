<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Homepage</title>
  </head>
  <body class="body1">
    <header>
      <p>BCC VISITOR LOG SYSTEM</p>
      <img src="image/BCClogo.png" id="logo" alt="Bago City College Logo" />
    </header>
    <main>
      <div class="options">
        <a href="check_in.jsp">
          <div class="nav_option">
            <p>Check-in</p>
          </div>
        </a>

        <a href="check_out.jsp">
          <div class="nav_option">
            <p>Check-out</p>
          </div>
        </a>

        <a href="daily_visitor_logs.jsp">
          <div class="nav_option">
            <p>Daily Visitor Logs</p>
          </div>
        </a>

        <a href="login.jsp">
          <div class="nav_option">
            <p>ADMIN</p>
          </div>
        </a>
      </div>
    </main>
  </body>
</html>
