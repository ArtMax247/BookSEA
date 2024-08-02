package book.servlet;

import book.dal.*;
import book.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet {

    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate fields.
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        //System.out.println("Received data: " + userName + ", " + password + ", " + confirmPassword + ", " + firstName + ", " + lastName + ", " + email + ", " + phone);

        if (userName == null || userName.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty() ||
            firstName == null || firstName.trim().isEmpty() ||
            lastName == null || lastName.trim().isEmpty()) {
            messages.put("error", "Please fill in all the fields.");
            //System.out.println("Error: Please fill in all the fields.");
        } else if (!password.equals(confirmPassword)) {
            messages.put("error", "Passwords do not match.");
            //System.out.println("Error: Passwords do not match.");
        } else {
            // Create the User
            try {
                Users newUser = new Users(userName, password, firstName, lastName, email, phone);
                newUser = usersDao.createUser(newUser);
                req.setAttribute("success", "Successfully created " + userName);
               // System.out.println("Success: Successfully created " + userName);
            } catch (SQLIntegrityConstraintViolationException e) {
                // Handle duplicate entry error
                req.setAttribute("error", "Username already exists. Please choose a different username.");
               // System.out.println("Error: Username already exists. Please choose a different username.");
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("error", "Error creating account. Please try again.");
                //System.out.println("Error: Error creating account. Please try again.");
            }
        }

        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
    }
}
