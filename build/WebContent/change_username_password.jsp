<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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

      /* MANAGE USERNAME AND PASSWORD*/
      .username_password_container {
            border: 3px solid black;
            border-radius: 6px;
            text-align: center;
            margin: auto;
            margin-top: 3rem;
        }

        .username_password_container h3 {
            font-size: clamp(1rem, 3vw, 1.6rem);
        }

        .username_password_container input {
            width: 80%;
            margin: 1rem;
            height: 3rem;
        }

        .username_password_container input[type = submit] {
            width: 30%;
            margin: 4rem;
            margin-bottom: 2rem;
            height: max-content;
        }

        .old_username_password {
            width: 100%;
            max-width: 70rem;
            margin: auto;
            padding: 2rem;
            box-sizing: border-box;

        }

        .new_username_password {
            width: 100%;
            max-width: 70rem;
            margin: auto;
            padding: 2rem;
            box-sizing: border-box;

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
          <div class="side_nav_content" ><a href="filtering.jsp">Filter Log History</a></div>
          <div class="side_nav_content" id="active_page">Manage Username & Password</div>
          <form action="LogOutServlet" method="post" class="contain_logout_button">
            <input type="submit" value="LOGOUT" id="log_out_button">
          </form>
        </nav>

        <main class="filter_main">
            <hr>

            <form action="ChangeUsernameAndPassword" method="post">
                <div class="username_password_container">
                    <div class="old_username_password" >
                        <h3>Enter old admin Username & Password</h3>
                        <input type="text" name="old_username" placeholder="Usercode" required>
                        <input type="password" name="old_password" placeholder="Password" required>
                    </div>
        
                    <div class="new_username_password">
                        <h3>Enter your new Username & Password</h3>
                        <input type="text" name="new_username" placeholder="Usercode">
                        <input type="password" name="new_password" placeholder="Password">
        
                        <input type="submit" value="Submit">
                    </div>
                </div>
            </form>

       </main>   
     </div>

</body>
</html>