package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.entities.Address;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewAddress")
public class AddressViewServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddressViewServlet.class.getName());
    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Start get viewAddress");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            Address address = addressDao.findByUserID(userId);
            request.setAttribute("address", address);
            getServletContext().getRequestDispatcher("/views/addresses/viewAddress.jsp").forward(request, response);
        } else {
            logger.error("Error user login");
            response.sendRedirect("/ishop/access/errorLogin.jsp");
        }
        logger.info("End get viewAddress");
    }
}
