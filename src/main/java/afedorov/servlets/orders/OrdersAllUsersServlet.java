package afedorov.servlets.orders;

import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.Order;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrdersAllUsers")
public class OrdersAllUsersServlet extends HttpServlet {
    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> orders = orderDao.findAll();
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/views/orders/viewAllUsersOrders.jsp").forward(request, response);
    }
}