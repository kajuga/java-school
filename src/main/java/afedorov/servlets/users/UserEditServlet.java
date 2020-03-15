package afedorov.servlets.users;

import afedorov.dao.impl.inmemory.UserDaoImpl;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import java.time.LocalDate;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        user.setRole(request.getParameter("role"));
        user.setMail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));
        try {
            userDao.update(Long.parseLong(request.getParameter("id")), user);
            response.sendRedirect(request.getContextPath() + "/viewUser");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            dispatcher.forward(request, response);
        } else {
            //Переход на страницу с просьюой авторизоваться
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            dispatcher.forward(request, response);
        }



    }
}
