// package assignment1;
// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class loginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// parameters
        String username = request.getParameter("username");

        UserBean user = new UserBean(username);
        HttpSession session = request.getSession();
        session.setAttribute("UserBean", username);

        // forward the user to newGame.jsp with their session data
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
	}
}


/*
User user = new User(username, userPass);
HttpSession session = request.getSession();
session.setAttribute("User", user);
// forward the user to loggedin.jsp with their session data
RequestDispatcher rd = request.getRequestDispatcher("Loggedin.jsp");
rd.forward(request, response);
*/