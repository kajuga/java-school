package afedorov.servlets.clients;

import afedorov.dao.impl.inmemory.ClientDaoImpl;
import afedorov.dao.interfaces.ClientDao;
import afedorov.entities.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewClient")
public class ClientViewServlet extends HttpServlet {
    private ClientDao clientDao = new ClientDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientDao.findAll();
        request.setAttribute("clients", clients);
        getServletContext().getRequestDispatcher("/views/clients/viewClient.jsp").forward(request, response);
    }
}
