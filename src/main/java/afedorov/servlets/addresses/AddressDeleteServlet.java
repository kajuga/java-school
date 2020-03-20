package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAddress")
public class AddressDeleteServlet extends HttpServlet {
    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("delete");
        try {
            addressDao.remove(Long.parseLong(id));
            response.sendRedirect(request.getContextPath() + "/viewAddress");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }
}