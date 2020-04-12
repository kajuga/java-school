package afedorov.servlets.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LogOutServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... logout start");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("index.jsp").include(request, response);
        HttpSession session=request.getSession();
        session.invalidate();
        out.print("You are successfully logged out!");
        out.close();
        logger.info("Login check... logout ok");
    }
}
