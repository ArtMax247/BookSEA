<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BookSEA:Login</title>
    <!-- Link to Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@700&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            color: white;
            font-family: Arial, sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url("/BookSEA/resources/img/faded-book.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-blend-mode: overlay;
            backdrop-filter: blur(4px);
        }
        .login-container {
            background-color: rgba(0, 0, 0, 0.65);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 400px;
            text-align: center;
        }
        .logo {
            display: block;
            margin: 0 auto 20px;
            height: 50px;
            width: 50px;
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
        .message.error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="site-name">BookSEA</div>
        <img src="${pageContext.servletContext.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
        <form action="login" method="post">
            <div class="form-field">
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div class="form-field">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-field">
                <button type="submit">Login</button>
            </div>
            <div class="form-field blue-button">
                <button type="button" onclick="window.location.href='CreateUser.jsp'">Create Account</button>
            </div>
            <% 
                String errorMessage = (String) request.getAttribute("error");
                
                if (errorMessage != null) { 
            %>
                <div class="message error">
                    <p><%= errorMessage %></p>
                </div>
            <% 
                } 
            %>
        </form>
    </div>
</body>
</html>
