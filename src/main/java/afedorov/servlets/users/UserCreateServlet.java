package afedorov.servlets.users;

import afedorov.dao.impl.inmemory.UserDaoImpl;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/createUser")
public class UserCreateServlet extends HttpServlet {
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        user.setRole(request.getParameter("role"));
        user.setMail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));
        try {
            userDao.add(user);
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect(request.getContextPath() + "/access/registerSuccesfull.jsp");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }


}