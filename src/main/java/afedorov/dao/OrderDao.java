package afedorov.dao;

import afedorov.entities.Order;
import afedorov.entities.OrderStatus;

import java.util.List;

public interface OrderDao {

    void add(Order order);
    List<Order> findAllByClient (Long clientId);
    List<Order> findAll ();

    void update(Order order); // todo при реализации разрешать с определенным статусом CREATED
    void repeatOrder(Long orderID);
    Order findById(Long orderID);


    void changeOrderStatus(Long orderID, OrderStatus status);
    void changePaymentStatus(Long orderID, OrderStatus status);



    //TODO /оформление заказа / просмотри истории заказа / (дополнительно) повторить заказ


    //TODO просмотр заказа // изменение статуса заказа

}
