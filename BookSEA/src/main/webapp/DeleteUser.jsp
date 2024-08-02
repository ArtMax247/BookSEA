<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BookSEA: Home</title>
    <!-- Link to Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@700&display=swap" rel="stylesheet">
    <!-- Link to Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            color: white;
            font-family: Arial, sans-serif;
            height: 100vh;
            display: flex;
            background-image: url("/BookSEA/resources/img/faded-book.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-blend-mode: overlay;
            backdrop-filter: blur(4px);
        }
        .left-section {
            width: 25%;
            position: relative;
            background-size: cover;
            background-position: center;
            padding: 20px;
            box-sizing: border-box;
            color: white;
            z-index: 1;
        }
        .right-section {
            width: 75%;
            position: relative;
            background-size: cover;
            background-position: center;
            justify-content: center;
            display: flex;
            align-items: center;
            padding: 20px;
            box-sizing: border-box;
            color: white;
            z-index: 1;
        }
        .manage-account-container {
            background-color: rgba(0, 0, 0, 0.65);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 400px;
            text-align: center;
        }
        .site-name {
            font-size: 24px;
            margin-bottom: 10px;
            font-family: 'Raleway', sans-serif;
        }
        .form-field {
            margin-bottom: 15px;
        }
        .form-field input {
            width: 93%;
            padding: 10px;
            border: none;
            border-radius: 5px;
        }
        .form-field button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 10px;
        }
        .form-field button:hover {
            background-color: #45a049;
        }
        
        .left-section::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.8); /* Black overlay with 80% opacity */
            z-index: -1;
        }
        .site-name-container {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .site-name {
            font-size: 24px;
            font-family: 'Raleway', sans-serif;
            margin-left: 10px;
        }
        .username {
            font-size: 16px;
            font-family: 'Arial', sans-serif;
            margin-left: 10px;
        }
        .navigation a {
            display: flex;
            align-items: center;
            color: white;
            text-decoration: none;
            margin-bottom: 10px;
            padding: 10px;
            background-color: rgba(76, 175, 80, 0.7);
            text-align: left;
            border-radius: 5px;
        }
        .navigation a i {
            margin-right: 10px;
        }
        .navigation a:hover {
            background-color: rgba(69, 160, 73, 0.7);
        }
        .header {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .logo {
            height: 50px;
            width: 50px;
        }
        .message.error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="left-section">
        <div class="site-name-container">
            <img src="${pageContext.servletContext.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
            <div>
                <div class="site-name">BookSEA</div>
            </div>
        </div>
        <div class="navigation">
            <a href="UpdateUser.jsp"><i class="fas fa-home"></i>Update Password</a>
            <a href="DeleteUser.jsp"><i class="fas fa-book"></i>DeleteAccount</a>
        </div>
    </div>
    <div class="right-section">
    <div class="manage-account-container">
		<div class="site-name">Delete Account</div>
        <form action="deleteUser" method="post">
            <div class="form-field">
                <input type="text" name="username" value="${username}" readonly>
            </div>
            <div class="form-field">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-field">
                <button type="submit" class="delete">Delete Account</button>
            </div>
            <% if ("true".equals(request.getParameter("error"))) { %>
                <p class="message error">Invalid password</p>
            <% } %>
           
        </form>
    </div>
    </div>
</body>
</html>

