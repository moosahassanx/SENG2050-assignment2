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
    // getting attributes of the same session
    HttpSession session = request.getSession();
    String cellPosition = request.getParameter("cellLabel");
    GameBean game = (GameBean)session.getAttribute("minesweeper");

    // serialising the GameBean object
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(bs);
    GameBean saveGame = game;
    try{
      os.writeObject(saveGame);
    }catch(NotSerializableException e){
      System.out.println("SERVLET WRITE OBJECT ERROR: " + e);
    }
    
    // closing tags
    os.flush();
    os.close();
    bs.close();
    
    // conversion to byte array
    byte[] gameOrWhatever = bs.toByteArray();

    // running saveGame() method
    try{
      game.saveGame(gameOrWhatever);
    }catch(Exception e){
      System.out.println(e);
    }
    
    // redirecting page
    response.sendRedirect("login.jsp");
  }
}