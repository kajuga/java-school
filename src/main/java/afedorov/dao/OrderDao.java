package afedorov.dao;

import afedorov.entities.Order;
import afedorov.entities.OrderStatus;
import afedorov.entities.PaymentState;

import java.util.List;

public interface OrderDao {

    void add(Order order);

    void update(Long id, Order newOrder); // todo при реализации разрешать с определенным статусом CREATED

    void repeatOrder(Long id);

    Order findById(Long id);

    List<Order> findAllByClient(Long clientId);

    List<Order> findAll();

    void changeOrderStatus(Long id, OrderStatus status);

    void changePaymentState(Long id, PaymentState state);
}
