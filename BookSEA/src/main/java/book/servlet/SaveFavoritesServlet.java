package book.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dal.UserFavouritesDao;

@WebServlet("/SaveFavoritesServlet")
public class SaveFavoritesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedGenres = request.getParameterValues("genres");
        UserFavouritesDao UFDao = UserFavouritesDao.getInstance();
     // Retrieve the current session
    	HttpSession session = request.getSession();
    	
    	// Get the username from the session
    	String username = (String) session.getAttribute("username");
        if (selectedGenres != null && selectedGenres.length <= 3) {
            List<String> genresList = Arrays.asList(selectedGenres);
            // Save genresList to the database for the logged-in user
            // Assume a method saveFavoriteGenres that takes userId and genresList as parameters
            UFDao.saveFavoriteGenres(username, genresList);
        } else {
            // Handle the error if more than 3 genres are selected
            response.sendRedirect("errorPage.jsp"); // Redirect to an error page
        }
        response.sendRedirect("readFavGenre"); // Redirect back to the favorite genres page
    }

}
