
// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.sql.*;

import beans.*;


@WebServlet(urlPatterns = {"/loadServlet"})
public class loadServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    GameBean game = (GameBean)session.getAttribute("minesweeper");
    String username = request.getParameter("username");
    
    try{
      game.loadGame(username);
    }catch(Exception e){
      System.out.println(e);
    }
    
    response.sendRedirect("game.jsp");
  }
}