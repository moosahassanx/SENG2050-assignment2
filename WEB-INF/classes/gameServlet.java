// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import beans.*;


@WebServlet(urlPatterns = {"/gameServlet"})
public class gameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      String cellPosition = request.getParameter("cellLabel");
      
      String[] afterSplit = cellPosition.split(" ");

      int cellX = Integer.parseInt(afterSplit[0]);
      int cellY = Integer.parseInt(afterSplit[1]);
      
      GameBean game = (GameBean)session.getAttribute("minesweeper");
      
      game.testCell(cellX, cellY);
      
      response.sendRedirect("game.jsp");
	}
}