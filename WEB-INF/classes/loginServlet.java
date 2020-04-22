// connect it to a bean
// connect it to database (but leave this to later)

// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import beans.UserBean;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class loginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = request.getParameter("username");

    UserBean userBeanObject = new UserBean(username);
    session.setAttribute("user", userBeanObject);

    response.sendRedirect("newgame.jsp");
	}
}
