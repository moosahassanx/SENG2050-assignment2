package pkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = ("/reservation"))
public class ReservationServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        ServletContext ctx = request.getServletContext();
        Restaurant restaurant = (Restaurant) ctx.getAttribute("restaurant");

        int idx = Integer.parseInt(request.getParameter("idx"));
        String name = request.getParameter("name");
        
        if(restaurant.isReserved(idx)){
            request.setAttribute("error", "Seat is booked or index is out of bounds.");
            ctx.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            return;
        }

        restaurant.book(idx, name);
        response.sendDirect("/mvc/index.jsp");
    }
}


public class ReservationsServlet {

}