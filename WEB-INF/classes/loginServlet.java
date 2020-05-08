import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// database usage
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.naming.InitialContext;
import java.util.*;

// importing beans package
import beans.UserBean;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class loginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // getting the session and paramaters passed from login.jsp
        HttpSession session = request.getSession();
        String username = request.getParameter("username");

        // creating a userbean and setting its attributes
        UserBean userBeanObject = new UserBean(username);
        session.setAttribute("user", userBeanObject);
        
        // redirecting user to new jsp
        response.sendRedirect("newgame.jsp");
    }
}
