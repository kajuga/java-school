package afedorov.servlets.orders;

import afedorov.dao.interfaces.UserDao;
import afedorov.settings.ServiceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/OrderViewServlet")
public class OrderViewServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/orders/orderConfirmation.jsp");
            dispatcher.forward(request, response);
        } else {
            //Переход на страницу с просьюой авторизоваться
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            dispatcher.forward(request, response);
        }




        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<h1> HELLO from  /OrderViewServlet </h1>");
        printWriter.println("</html>");



    }
}
