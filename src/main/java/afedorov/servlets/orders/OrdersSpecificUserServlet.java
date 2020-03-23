package afedorov.servlets.orders;

import afedorov.dao.interfaces.OrderDao;
import afedorov.dao.interfaces.ProductDao;
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

@WebServlet("/OrdersSpecificUser")
public class OrdersSpecificUserServlet extends HttpServlet {
        private OrderDao orderDao;

        @Override
        public void init() throws ServletException {
            orderDao = ServiceManager.getInstance(getServletContext()).getOrderDao();
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
            Long userId = (Long)session.getAttribute("userId");
            List<Order> orders = orderDao.findAllByUser(userId);
            request.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/views/orders/viewSpecificUserOrders.jsp").forward(request, response);
    }
}
