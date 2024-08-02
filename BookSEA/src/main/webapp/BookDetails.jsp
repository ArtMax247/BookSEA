<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BookSEA: Book Details</title>
    <!-- Link to Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@700&display=swap" rel="stylesheet">
    <!-- Link to Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            font-family: Arial, sans-serif;
            background-image: url('${pageContext.servletContext.contextPath}/resources/img/faded-book.jpg');
            background-size: cover;
            background-position: center;
            color: white;
        }
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.8); /* Black overlay with 80% opacity */
        }
        .container {
            display: flex;
            width: 100%;
            height: 100%;
            position: relative;
            z-index: 1;
        }
        .left-section {
            width: 40%;
            padding-left: 20px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: left;
        }
        .right-section {
            width: 60%;
            padding-right: 30px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            justify-content: center;
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
            justify-content: center;
            width: 100px;
            height: 10px;
            color: white;
            text-decoration: none;
            margin-bottom: 20px;
            padding: 10px;
            background-color: rgba(76, 175, 80, 0.7);
            text-align: center;
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
        .book-details {
            list-style: none;
            padding: 0;
            width: 100%;
        }
        .book-details li {
            margin-bottom: 15px;
            padding: 10px;
            background-color: #333;
            border-radius: 5px;
        }
        .book-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .book-title::after {
            content: '';
            display: block;
            width: 100%;
            height: 1px;
            background-color: grey;
            margin-top: 5px;
        }
        .info_enc {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .info {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .info b {
            font-weight: bold;
            color: grey;
            }
        .book-title {
            font-weight: bold;
        }
        .logo {
            height: 50px;
            width: 50px;
        }
        .book-image-container {
        	padding-left: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-grow: 1;
            width: 100%;
        }
        .book-image {
            width: 75%;
            height: auto;
            object-fit: cover; /* Maintain aspect ratio while covering the container */
        }
    </style>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <div class="left-section">
            <div class="site-name-container">
                <img src="${pageContext.servletContext.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
                <div class="site-name">BookSEA</div>
            </div>
            <div class="navigation">
                <a href="javascript:history.back()"><i class="fas fa-arrow-left"></i>Back</a>
            </div>
            <div class="book-image-container">
            <c:choose>
                <c:when test="${fn:trim(book.imgURL) != ''}">
                    <img src="${book.imgURL}" alt="Book Image" class="book-image">
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.servletContext.contextPath}/resources/img/default-book.jpg" alt="Default Book Image" class="book-image">
                </c:otherwise>
            </c:choose>
            </div>
        </div>
        <div class="right-section">
        <div class = "info_enc">
            <div class="book-title">${book.title}</div>
            <div class="info"><b>Author:</b> ${book.author}</div>
            <div class="info"><b>Published Year:</b> ${book.publishedYear}</div>
            <div class="info"><b>Genre:</b> ${book.genre}</div>
            <div class="info"><b>Amazon Rating:</b> ${book.ARating}</div>
            <div class="info"><b>Goodreads Rating:</b> ${book.GRating}</div>
            <div class="info"><b>NYT Best Seller:</b> ${book.NRating}</div>
            <div class="info"><b>Overall SEA Library Checkout:</b> ${book.checkout}</div>
            </div>
        </div>
    </div>
</body>
</html>
