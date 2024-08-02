<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BookSEA: My Favorite Genre</title>
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
        .book-list {
            list-style: none;
            padding: 0;
        }
        .book-list li {
            margin-bottom: 15px;
            padding: 10px;
            background-color: #333;
            border-radius: 5px;
        }
        .book-title {
            font-weight: bold;
        }
        .logo {
            height: 50px;
            width: 50px;
        }
        .checkbox-container {
            margin-bottom: 20px;
        }
        .selected-genres {
            margin-top: 20px;
        }
        .selected-genres ul {
            list-style: none;
            padding: 0;
        }
        .selected-genres li {
            margin-bottom: 10px;
            padding: 10px;
            background-color: #333;
            border-radius: 5px;
        }
    </style>
    <script>
        function updateSelectedGenres() {
            const checkboxes = document.querySelectorAll('input[name="genres"]:checked');
            const selectedGenres = [];
            checkboxes.forEach(checkbox => selectedGenres.push(checkbox.value));
            if (selectedGenres.length > 3) {
                alert('You can select a maximum of 3 genres.');
                return false;
            }
            const selectedGenresContainer = document.getElementById('selectedGenres');
            selectedGenresContainer.innerHTML = '';
            selectedGenres.forEach(genre => {
                const li = document.createElement('li');
                li.textContent = genre;
                selectedGenresContainer.appendChild(li);
            });
            return true;
        }

        function limitCheckboxSelection(event) {
            const checkboxes = document.querySelectorAll('input[name="genres"]:checked');
            if (checkboxes.length > 3) {
                alert('You can select a maximum of 3 genres.');
                event.target.checked = false;
                return false;
            }
            updateSelectedGenres();
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
        <div class="header">My Favorite Genre</div>
        <div class="checkbox-container">
            <label>Select Your Favorite Genres (Max 3):</label>
            <form action="SaveFavoritesServlet" method="post">
                <c:forEach var="genre" items="${genre}">
                    <div>
                        <input type="checkbox" id="${genre}" name="genres" value="${genre}" 
                            <c:if test="${fn:contains(selectedGenres, genre)}">checked</c:if>
                            onchange="limitCheckboxSelection(event)">
                        <label for="${genre}">${genre}</label>
                    </div>
                </c:forEach>
                <button type="submit">Save</button>
            </form>
        </div>
        <div class="selected-genres">
            <div class="header">Selected Genres:</div>
            <ul id="selectedGenres">
                <c:forEach var="genre" items="${selectedGenres}">
                    <li>${genre}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
