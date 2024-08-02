package book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dal.UsersDao;
import book.model.Users;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Users user = usersDao.getUserByUserNameAndPassword(userName, password);

            if (user != null) {
                // Successful login
                HttpSession session = req.getSession();
                session.setAttribute("username", userName);
                resp.sendRedirect(req.getContextPath() + "/readBook");
            } else {
                // Invalid credentials
                req.setAttribute("error", "Invalid username or password. Please try again.");
                req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error logging in. Please try again.");
            req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
        }
    }
}
