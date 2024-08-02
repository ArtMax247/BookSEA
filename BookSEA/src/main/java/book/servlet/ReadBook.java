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
import book.dal.SeattleLibraryDao;
import book.dal.UserFavouritesDao;
import book.model.Books;

@WebServlet("/readBook")
public class ReadBook extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String searchQuery = request.getParameter("searchQuery");
        String filter = request.getParameter("filter");
    	
    	
    	SeattleLibraryDao SLDao = SeattleLibraryDao.getInstance();
    	BooksDao bookDAO = BooksDao.getInstance();
    	
    	List<Books> topBooks = null;
    	List<Books> searchResults = null;
    	List<String> str = new ArrayList<String>();
    	
    	// Retrieve the current session
    	HttpSession session = request.getSession();
    	
    	// Get the username from the session
    	String username = (String) session.getAttribute("username");
    	
    	//System.out.println(username);
    	
    	
    	try {
			topBooks = SLDao.getTopBooks();
			
			
			if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	            if (filter != null && !filter.trim().isEmpty()) {
	                switch (filter) {
	                    case "title":
	                    	//System.out.println("title");
							searchResults = bookDAO.getBookByName(searchQuery);
							System.out.println(searchResults);
	                        break;
	                    case "author":
	                    	//System.out.println("author");
	                        searchResults = bookDAO.getBookByAuthor(searchQuery);
	                        break;
	                    default:
	                        searchResults = bookDAO.getBooks();
	                        break;
	                }
	            } else {
	                searchResults = bookDAO.getBooks();
	            }
	        } else {
	            searchResults = bookDAO.getBooks();
	        }
			
			//System.out.println(topBooks);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // Set books as attributes to be accessed in JSP
        request.setAttribute("topBooks", topBooks);
        request.setAttribute("searchResults", searchResults);
        
        
        // Forward to JSP
        request.getRequestDispatcher("ReadBook.jsp").forward(request, response);
    }
}
