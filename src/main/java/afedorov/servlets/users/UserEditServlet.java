package afedorov.servlets.users;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserEditServlet.class.getName());
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST editUser");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setBirthDate(Date.valueOf(request.getParameter("birthDate")));
        user.setRole(request.getParameter("role"));
        user.setMail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));
        try {
            userDao.update(Long.parseLong(request.getParameter("id")), user);
            logger.info("Login check... end POST editUser");
            response.sendRedirect(request.getContextPath() + "/editUser");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.error("Error POST editUser", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET editUser");
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            User user = userDao.findById(userId);
            request.setAttribute("id", userId);
            request.setAttribute("name", user.getName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("birthDate", user.getBirthDate());
            request.setAttribute("role", user.getRole());
            request.setAttribute("mail", user.getMail());
            request.setAttribute("password", user.getPassword());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/users/updateUser.jsp");
            logger.info("Login check... end GET editUser");
            dispatcher.forward(request, response);
        } else {
            //Переход на страницу с просьюой авторизоваться
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            dispatcher.forward(request, response);
        }
    }
}