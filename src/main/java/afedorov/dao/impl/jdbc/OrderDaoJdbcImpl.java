package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.Order;
import afedorov.entities.OrderStatus;
import afedorov.entities.PaymentState;

import java.util.List;

public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public void add(Order order) {

    }

    @Override
    public void update(Long id, Order newOrder) {

    }

    @Override
    public void repeatOrder(Long id) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAllByUser(Long userId) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {

    }

    @Override
    public void changePaymentState(Long id, PaymentState state) {

    }
}
