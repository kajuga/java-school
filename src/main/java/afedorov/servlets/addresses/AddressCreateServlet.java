package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Address;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import afedorov.servlets.access.LoginCheckServlet;
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

@WebServlet("/createAddress")
public class AddressCreateServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddressCreateServlet.class.getName());
    private AddressDao addressDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start create address of user with id {}", request.getParameter("userId"));
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {
            Address address = new Address();
            address.setUser(userDao.findById((Long) (session.getAttribute("userId"))));
            address.setCountry(request.getParameter("country"));
            address.setCity(request.getParameter("city"));
            address.setPostcode(Integer.parseInt(request.getParameter("postcode")));
            address.setStreet(request.getParameter("street"));
            address.setHouseNumber(request.getParameter("houseNumber"));
            address.setRoom(request.getParameter("room"));
            address.setPhone(request.getParameter("phone"));
            try {
                addressDao.add(address);
                logger.info("Login check... address created");
                response.sendRedirect(request.getContextPath() + "/views/addresses/addressSuccessfulCreated.jsp");
            } catch (EntityExistException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println(e.getMessage());
                logger.error("Error adding addres", e);
            }
        } else {
            logger.info("Login check.. login error");
            response.sendRedirect("/ishop/access/errorLogin.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start find address for user with id {}", request.getParameter("userId"));

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        User user = userDao.findById(userId);
        request.setAttribute("user", user);
        logger.info("Login check... find end");
        getServletContext().getRequestDispatcher("/views/addresses/createAddress.jsp").forward(request, response);
    }
}
