package afedorov.servlets.orders;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.OrderDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Order;
import afedorov.entities.OrderStatus;
import afedorov.entities.ProductInCart;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/createOrder")
public class OrderCreatedServlet extends HttpServlet {
    private OrderDao orderDao;
    private UserDao userDao;
    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        Long addressId = (Long) request.getAttribute("addressId");
        Map<ProductInCart, Integer> shopCart =  (Map<ProductInCart, Integer>) httpSession.getAttribute("shopCart");

        Order order = new Order();
        order.setUser(userDao.findById(userId));
//        order.setAddress(addressDao.findByID(addressId));
//        order.setPaymentMethod();
//        order.setDeliveryMethod();
        order.setProducts(shopCart);
//        order.setPaymentState();
        order.setOrderStatus(OrderStatus.CREATED);
        orderDao.add(order);


        httpSession.setAttribute("shopCart", null);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
