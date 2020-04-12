package afedorov.servlets.users;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewUser")
public class UserViewServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserViewServlet.class.getName());
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET viewUser");
        List<User> users = userDao.findAll();
        request.setAttribute("users", users);
        logger.info("Login check... end GET viewUser");
        getServletContext().getRequestDispatcher("/views/users/viewUser.jsp").forward(request, response);
    }
}