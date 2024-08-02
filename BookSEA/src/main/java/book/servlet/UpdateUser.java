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

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (newPassword.equals(confirmPassword) && isCurrentPasswordValid(userName, currentPassword)) {
            updatePassword(userName, newPassword);
            resp.sendRedirect("UpdateUser.jsp?error=false");
        } else {
            resp.sendRedirect("UpdateUser.jsp?error=true");
        }
    }

    private boolean isCurrentPasswordValid(String userName, String currentPassword) {
    	try {
    		UsersDao usersDao = UsersDao.getInstance();
            Users user = usersDao.getUserByUserNameAndPassword(userName, currentPassword);

            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }

    private void updatePassword(String userName, String newPassword) {
        // Implement the logic to update the password in the database
    	UsersDao usersDao = UsersDao.getInstance();
        try {
			usersDao.updatePasswordByUserName(userName, newPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

