package afedorov.servlets.clients;

import afedorov.dao.impl.inmemory.ClientDaoImpl;
import afedorov.dao.interfaces.ClientDao;
import afedorov.exceptions.EntityExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteClient")
public class ClientDeleteServlet extends HttpServlet {
    private ClientDao clientDao = new ClientDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("delete");
        try {
            clientDao.remove(Long.parseLong(id));
            response.sendRedirect(request.getContextPath() + "/viewClient");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }
}