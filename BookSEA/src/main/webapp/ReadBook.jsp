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
            height: 100vh;
            display: flex;
            font-family: Arial, sans-serif;
        }
        .left-section {
            width: 25%;
            position: relative;
            background-image: url('${pageContext.servletContext.contextPath}/resources/img/faded-book.jpg');
            background-size: cover;
            background-position: center;
            padding: 20px;
            box-sizing: border-box;
            color: white;
            z-index: 1;
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
        .right-section {
            width: 75%;
            background-color: black;
            padding: 20px;
            box-sizing: border-box;
            color: white;
            height: 100vh; /* Ensures it takes full height of the viewport */
            overflow-y: auto; /* Enables vertical scrolling */
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
            font-size: 20px;
            margin-bottom: 20px;
            color: green;
        }
        .section {
            margin-bottom: 40px; /* Adds space between sections */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            font-size: 16px;
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            color: white;
        }
        td {
            font-size: 14px;
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            color: white;
        }
        th {
            background-color: #333;
        }
        tr:hover {
            background-color: #555;
            cursor: pointer;
        }
        .logo {
            height: 50px;
            width: 50px;
        }
        .icon {
            cursor: pointer;
            color: green;
        }
        .icon2 {
            cursor: pointer;
            color: grey;
        }
        .search-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .search-bar {
            display: flex;
            align-items: center;
        }
        .search-bar input[type="text"] {
            padding: 10px;
            border: none;
            border-radius: 5px 0 0 5px;
            width: 600px;
        }
        .search-bar button {
            padding: 10px;
            border: none;
            border-radius: 0 5px 5px 0;
            background-color: green;
            color: white;
            cursor: pointer;
        }
        .filter-section {
            display: flex;
            align-items: center;
        }
        .filter-section select {
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: white;
            color: black;
            width: 300px;
        }
    </style>
    <script>
        function setFilter() {
            var filter = document.getElementById("filterSelect").value;
            document.getElementById("filterInput").value = filter;
        }
        function goToBookDetails(isbn) {
            window.location.href = 'bookDetails?isbn=' + isbn;
        }
    </script>
</head>
<body>
    <div class="left-section">
        <div class="site-name-container">
            <img src="${pageContext.servletContext.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
            <div>
                <div class="site-name">BookSEA</div>
                <div class="username">Welcome, ${username}!</div>
            </div>
        </div>
        <div class="navigation">
            <a href="readBook"><i class="fas fa-home"></i>Home</a>
            <a href="readReadingList"><i class="fas fa-book"></i>Reading List</a>
            <a href="readRecommendation"><i class="fas fa-thumbs-up"></i>My Recommendations</a>
            <a href="readFavGenre"><i class="fas fa-heart"></i>My Favorite Genre</a>
            <a href="UpdateUser.jsp"><i class="fas fa-user-cog"></i>Manage Account</a>
            <a href="UserLogin.jsp"><i class="fas fa-sign-out-alt"></i>Logout</a>
        </div>
    </div>
    <div class="right-section">
        <div class="section">
            <div class="header">Trending Books This Month</div>
            <table>
                <thead>
                    <tr>
                    	<th></th>
                        <th>Title</th>
                        <th>Author</th>
                        
                        <th>Genre</th>
                        <th>Add to Reading List</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${topBooks}">
                        <tr onclick="goToBookDetails('${book.ISBN}')">
                        	<td>
                                <a href="AddToRecommendationServlet?isbn=${book.ISBN}" class="icon2" onclick="event.stopPropagation();">
                                    <i class="fas fa-thumbs-up"></i>
                                </a>
                            </td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            
                            <td>${book.genre}</td>
                            <td>
                                <a href="AddToReadingListServlet?isbn=${book.ISBN}" class="icon" onclick="event.stopPropagation();">
                                    <i class="fas fa-plus"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="section">
            <div class="header">Browse Books</div>
            <div class="search-section">
                <div class="search-bar">
                    <form action="readBook" method="get">
                        <input type="text" name="searchQuery" placeholder="Search books...">
                        <input type="hidden" id="filterInput" name="filter" value="">
                        <button type="submit"><i class="fas fa-search"></i> Search</button>
                    </form>
                </div>
                <div class="filter-section">
                    <select id="filterSelect" onchange="setFilter()">
                        <option value="">Select Filter</option>
                        <option value="title">Title</option>
                        <option value="author">Author</option>
                    </select>
                </div>
            </div>
            <table>
                <thead>
                    <tr>
                    	<th></th>
                        <th>Title</th>
                        <th>Author</th>
                        
                        <th>Genre</th>
                        <th>Add to Reading List</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${searchResults}">
                        <tr onclick="goToBookDetails('${book.ISBN}')">
                        	<td>
                                <a href="AddToRecommendationServlet?isbn=${book.ISBN}" class="icon2" onclick="event.stopPropagation();">
                                    <i class="fas fa-thumbs-up"></i>
                                </a>
                            </td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            
                            <td>${book.genre}</td>
                            <td>
                                <a href="AddToReadingListServlet?isbn=${book.ISBN}" class="icon" onclick="event.stopPropagation();">
                                    <i class="fas fa-plus"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
