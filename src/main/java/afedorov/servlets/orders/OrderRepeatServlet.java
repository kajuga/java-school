package afedorov.servlets.orders;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.OrderDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Order;
import afedorov.entities.ProductInCart;
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
import java.util.Map;

@WebServlet("/orderRepeat")
public class OrderRepeatServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepeatServlet.class.getName());
    private UserDao userDao;
    private AddressDao addressDao;
    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
        orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST orderRepeat");
        HttpSession session = request.getSession();
        Long orderId = Long.parseLong(request.getParameter("order_id"));
        Order order = orderDao.findById(orderId);
        Map<ProductInCart, Integer> cart = order.getProducts();
        session.setAttribute("shoppingCart", cart);
        logger.info("Login check... end POST orderRepeat, go forward");
        getServletContext().getRequestDispatcher("/views/shoppingCart/viewShoppingCart.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET orderRepeat");
        HttpSession session = request.getSession();
        Long orderId = (Long)session.getAttribute("order_id");
        Order order = orderDao.findById(orderId);
        request.setAttribute("order", order);
        logger.info("Login check... end GET orderRepeat, go forward");
        getServletContext().getRequestDispatcher("/views/orders/viewSpecificUserOrders.jsp").forward(request, response);
    }
}