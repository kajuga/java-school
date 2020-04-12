package afedorov.servlets.users;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/createUser")
public class UserCreateServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserCreateServlet.class.getName());
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST createUser");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setBirthDate(Date.valueOf(request.getParameter("birthDate")));
        user.setRole(request.getParameter("role"));
        user.setMail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));
        try {
            userDao.add(user);
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("role", user.getRole());
            logger.info("Login check... end POST createUser");
            response.sendRedirect(request.getContextPath() + "/access/registerSuccesfull.jsp");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.error("Error createUser", e);
        }
    }
}