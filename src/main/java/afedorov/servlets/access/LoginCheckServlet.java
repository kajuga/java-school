package afedorov.servlets.access;

import afedorov.dao.impl.inmemory.UserDaoImpl;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginCheck")
public class LoginCheckServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getParameter("mail").isEmpty()  && request.getParameter("password").isEmpty())) {
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            User user = userDao.findByMail(mail);
            if (user != null && user.getPassword().equals(password)) {
                response.sendRedirect("/ishop/access/userDisplay.jsp");
                request.getSession().setAttribute("userId", user.getId());

            } else {
                response.sendRedirect("/ishop/access/errorLogin.jsp");
            }
        } else {
            response.sendRedirect("/ishop/access/errorLogin.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

//
//    RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
//            dispatcher.forward(request, response);