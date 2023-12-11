<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Update</title>

    <style>
        body {
            background-color: hsl(233, 22%, 92%);
            font-family: Verdana, Geneva, Tahoma, sans-serif;
        }

        .modal_update {
            width: 100%;
            max-width: 30rem;
            margin: 0 auto;
            border-radius: 0.7rem;
            box-shadow: 0px 0px 0.5rem;
            min-height: 15rem;
            padding-top: 3rem;
            background-color: rgba(240, 248, 255, 0.455);

            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        hr {
            z-index: 1;
            color: black;
        }

        button {
            border-radius: 50%;
            width: 2rem;
            height: 2rem;

            position: absolute;
            top: 0.8rem;
            right: 1rem;
            border: 1px solid hsl(240, 1%, 54%);
            color: hsl(240, 1%, 54%);
        }

        button:active {
            transform: rotate(360deg);
            transition: 1s linear;
        }

        .modal_update div {
            position: absolute;
            top: 55%;
            transform: translateY(-50%);
        }

        .modal_update div b {
            font-size: clamp(1.8rem, 2vw, 2.3rem);
            color: rgb(19, 166, 19);
            margin-left: 3rem;
        }

        .modal_update div p {
            margin: 1rem;
            margin-left: 3rem;
            font-size: clamp(1.2rem, 1rem, 1.6rem);
        }

    </style>
</head>
<body>

    <div class="modal_update">
        
        <hr>
        <% String updateStatus = (String) request.getAttribute("UpdateStatus"); %>
        <% String message = (String) request.getAttribute("resultMessage"); %>
        <div>
        	<% if(updateStatus != null) { %>
        		<b style="color: red;"> <%= updateStatus %></b> <%
        	} else {
        	%>
            <b > Update Success</b>
            <%} %>
            <p><%= message %></p>
        </div>
        <a href="change_username_password.jsp"><button>X</button></a>

    </div>
    
</body>
</html>