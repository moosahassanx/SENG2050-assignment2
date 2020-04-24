// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


@WebServlet(urlPatterns = {"/gameServlet"})
public class gameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      String cellPosition = request.getParameter("cell");
      
      
      
      response.sendRedirect("game.jsp");
	}
}