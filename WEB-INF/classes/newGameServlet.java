// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import beans.UserBean;
import beans.GameBean;

@WebServlet(urlPatterns = {"/newGameServlet"})
public class newGameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      String username = request.getParameter("username");
      String difficulty = request.getParameter("gameDifficulty");
      
      GameBean gameBeanObject = new GameBean(difficulty);
      session.setAttribute("minesweeper", gameBeanObject);
      
      response.sendRedirect("game.jsp");
	}
}

/* LOGINSERVLET.JAVA
HttpSession session = request.getSession();
String username = request.getParameter("username");

UserBean userBeanObject = new UserBean(username);    
session.setAttribute("user", userBeanObject);

response.sendRedirect("newgame.jsp");
*/

/* OLD SAVE
// parameters
String username = request.getParameter("username");
String gameDifficulty = request.getParameter("gameDifficulty");

UserBean user = new UserBean(username);
HttpSession session = request.getSession();
session.setAttribute("UserBean", username);

// forward the user to newGame.jsp with their session data
RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp");
requestDispatcher.forward(request, response);
*/