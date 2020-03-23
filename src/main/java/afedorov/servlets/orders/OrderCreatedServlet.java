package afedorov.servlets.orders;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.OrderDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.*;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
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
        orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Order order = new Order();
        User user = userDao.findById((Long) httpSession.getAttribute("userId"));
        Address address = addressDao.findByUserID(user.getId());
        Map<ProductInCart, Integer> shopCart =  (Map<ProductInCart, Integer>) httpSession.getAttribute("shoppingCart");

        order.setUser(user);
        order.setAddress(address);
        order.setDeliveryMethod(DeliveryMethod.fromKey(request.getParameter("deliveryMethod")));
        order.setPaymentMethod(PaymentMethod.fromKey(request.getParameter("paymentMethod")));
        order.setProducts(shopCart);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setPaymentState(PaymentState.AWAITING_PAYMENT);
        order.setOrderCost(new BigDecimal(request.getParameter("countTotal")));
        try {
        orderDao.add(order);
        httpSession.setAttribute("shoppingCart", null);

        response.sendRedirect(request.getContextPath() + "/access/registerSuccesfull.jsp");
    } catch (
    EntityExistException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println(e.getMessage());
    }
}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
