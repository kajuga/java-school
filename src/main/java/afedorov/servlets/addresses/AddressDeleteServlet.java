package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
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

@WebServlet("/deleteAddress")
public class AddressDeleteServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddressDeleteServlet.class.getName());

    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start address delete");

        String id = request.getParameter("delete");
        try {
            addressDao.remove(Long.parseLong(id));
            response.sendRedirect(request.getContextPath() + "/viewAddress");
            logger.info("Login check... end");

        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.error("Error deleting address", e);
        }
    }
}