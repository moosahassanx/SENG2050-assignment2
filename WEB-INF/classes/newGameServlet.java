// package assignment1;
// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/newGameServlet"})
public class newGameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// parameters
        String username = request.getParameter("username");
        String gameDifficulty = request.getParameter("gameDifficulty");

        
	}
}