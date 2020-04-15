import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import beans.UserBean;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean bean = new UserBean(username, password);
        session.setAttribute("user", bean);

        response.sendRedirect("currentuser.jsp");
    }
}