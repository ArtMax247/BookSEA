package book.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dal.UsersDao;
import book.model.Users;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Use false to avoid creating a new session if one does not exist

        String userName = (String) session.getAttribute("username");
        String password = req.getParameter("password");
        
       // System.out.println(userName);
        
        if (isCurrentPasswordValid(userName, password)) {
            UsersDao usersDao = UsersDao.getInstance();
            try {
            	//System.out.println("2");
            	
                usersDao.delete(userName, password);
                //System.out.println("5");
                session.invalidate(); // Invalidate the session after deleting the user
                //req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
                resp.sendRedirect("UserLogin.jsp");
            } catch (SQLException e) {
            	//System.out.println("4");
                e.printStackTrace();
                resp.sendRedirect("DeleteUser.jsp?error=server");
            }
        } else {
        	//System.out.println("3");
            resp.sendRedirect("DeleteUser.jsp?error=true");
        }
    }
    
    private boolean isCurrentPasswordValid(String userName, String currentPassword) {
        try {
        	//System.out.println("1");
            UsersDao usersDao = UsersDao.getInstance();
            Users user = usersDao.getUserByUserNameAndPassword(userName, currentPassword);
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
