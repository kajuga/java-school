package afedorov.servlets.clients;

import afedorov.dao.impl.inmemory.ClientDaoImpl;
import afedorov.dao.interfaces.ClientDao;
import afedorov.entities.Client;
import afedorov.exceptions.EntityExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/createClient")
public class ClientCreateServlet extends HttpServlet {
    ClientDao clientDao = new ClientDaoImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = new Client();
        client.setName(request.getParameter("name"));
        client.setLastName(request.getParameter("lastName"));
        client.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        client.setMail(request.getParameter("mail"));
        client.setPassword(request.getParameter("password"));
        try {
            clientDao.add(client);
            response.sendRedirect(request.getContextPath() + "/viewClient");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientDao.findAll();
        request.setAttribute("clients", clients);
        getServletContext().getRequestDispatcher("/views/clients/createClient.jsp").forward(request, response);
    }
}