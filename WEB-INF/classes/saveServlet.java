// Import required java libraries
import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.sql.*;

import beans.*;


@WebServlet(urlPatterns = {"/saveServlet"})
public class saveServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String cellPosition = request.getParameter("cellLabel");
    
    GameBean game = (GameBean)session.getAttribute("minesweeper");

    // serialising the GameBean object
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(bs);
    GameBean saveGame = game;

    try{
      os.writeObject(saveGame);                                           // tomcat messes up here
    }catch(NotSerializableException e){
      System.out.println("SERVLET ERROR: " + e);
    }
    
    // closing
    os.flush();
    os.close();
    bs.close();
    
    byte[] gameOrWhatever = bs.toByteArray();

    try{
      game.saveGame(gameOrWhatever);
    }catch(Exception e){
      System.out.println(e);
    }
    
    response.sendRedirect("login.jsp");
  }
}