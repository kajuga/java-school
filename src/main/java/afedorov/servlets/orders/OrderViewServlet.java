package afedorov.servlets.orders;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.OrderDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Address;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OrderViewServlet")
public class OrderViewServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderViewServlet.class.getName());
    private UserDao userDao;
    private AddressDao addressDao;
    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
        orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET OrderViewServlet");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Address address = addressDao.findByUserID(userId);
            request.setAttribute("address", address);
            logger.info("Login check... end GET OrderViewServlet, forward");
            getServletContext().getRequestDispatcher("/views/orders/orderConfirmation.jsp").forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            logger.info("Login check... errorlogin in GET OrderViewServlet");
            dispatcher.forward(request, response);
        }
    }
}