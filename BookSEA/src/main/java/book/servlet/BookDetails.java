package book.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dal.BooksDao;
import book.dal.ReadingListDao;
import book.model.Books;

@WebServlet("/bookDetails")
public class BookDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	BooksDao BDao = BooksDao.getInstance();
    	Books book = null;
    	
    	HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String isbn = request.getParameter("isbn");

        // Add the book to the user's reading list
        try {
        	//System.out.println("Deleting Reading List");
			book = BDao.getBookDetailsByISBN(isbn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.setAttribute("book", book);
        // Redirect back to the home page or the previous page
        request.getRequestDispatcher("BookDetails.jsp").forward(request, response);
    }


}