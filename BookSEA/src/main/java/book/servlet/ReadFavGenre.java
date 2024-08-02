package book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dal.BooksDao;
import book.dal.ReadingListDao;
import book.dal.SeattleLibraryDao;
import book.dal.TrendingTikTokDao;
import book.dal.UserFavouritesDao;
import book.model.Books;

@WebServlet("/readFavGenre")
public class ReadFavGenre extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserFavouritesDao UFDao = UserFavouritesDao.getInstance();
    	TrendingTikTokDao TTDao = TrendingTikTokDao.getInstance();
    	List<String> SelectedgenresList = null;
    	List<String> genresList = null;
    	
    	// Retrieve the current session
    	HttpSession session = request.getSession();
    	
    	// Get the username from the session
    	String username = (String) session.getAttribute("username");
    	
    	try {
    		SelectedgenresList = UFDao.getUserFavouritesByUserName(username);
    		genresList = TTDao.getGenre();
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // Set books as attributes to be accessed in JSP
        request.setAttribute("genre", genresList);
        request.setAttribute("selectedGenres", SelectedgenresList);
        
        // Forward to JSP
        request.getRequestDispatcher("ReadFavGenre.jsp").forward(request, response);
    }
}
