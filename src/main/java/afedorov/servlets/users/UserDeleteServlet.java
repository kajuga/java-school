package afedorov.servlets.users;

import afedorov.dao.impl.inmemory.UserDaoImpl;
import afedorov.dao.interfaces.UserDao;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class UserDeleteServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserDeleteServlet.class.getName());
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET deleteUser");
        Long userId = (Long) request.getSession().getAttribute("userId");
        String id = request.getParameter("delete");
        if (userId != null && userId.equals(Long.parseLong(id))) {
            try {
                userDao.remove(Long.parseLong(id));
                logger.info("Login check... end GET deleteUser");
                response.sendRedirect(request.getContextPath() + "/viewUser");
            } catch (EntityExistException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println(e.getMessage());
                logger.error("Error  GET deleteUser", e);

            }
        } else {
            //Переход на страницу с просьюой авторизоваться
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            dispatcher.forward(request, response);
        }
    }
}