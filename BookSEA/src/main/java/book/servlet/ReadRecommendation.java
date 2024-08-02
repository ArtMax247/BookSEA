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
import book.dal.RecommendationsDao;
import book.dal.SeattleLibraryDao;
import book.dal.UserFavouritesDao;
import book.model.Books;

@WebServlet("/readRecommendation")
public class ReadRecommendation extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RecommendationsDao RDao = RecommendationsDao.getInstance();
    	
    	List<Books> recommendations = null;
    	
    	// Retrieve the current session
    	HttpSession session = request.getSession();
    	
    	// Get the username from the session
    	String username = (String) session.getAttribute("username");
    	
    	try {
    		recommendations = RDao.getRecommendationFromUserName(username);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // Set books as attributes to be accessed in JSP
        request.setAttribute("recommendations", recommendations);
        
        // Forward to JSP
        request.getRequestDispatcher("ReadRecommendation.jsp").forward(request, response);
    }
}
