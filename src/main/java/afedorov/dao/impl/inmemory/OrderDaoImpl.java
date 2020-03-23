package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.Order;
import afedorov.entities.OrderStatus;
import afedorov.entities.PaymentState;

import java.util.ArrayList;
import java.util.List;

import static afedorov.dao.impl.inmemory.InMemoryDataBase.ORDERS;

public class OrderDaoImpl implements OrderDao {
    private Long idGenerator = 1l;

    @Override
    public void add(Order order) {
        if (order != null) {
            order.setId(idGenerator);
            idGenerator++;
            ORDERS.add(order);
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            for (Order finded : ORDERS) {
                if (id.equals(finded.getId())) {
                    return finded;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Long id, Order order) {
        if (id != null) {
            Order updatable = findById(id);
            if (updatable.getOrderStatus().equals(OrderStatus.CREATED)) {
                updatable.setAddress(order.getAddress());
                updatable.setPaymentMethod(order.getPaymentMethod());
                updatable.setDeliveryMethod(order.getDeliveryMethod());
                updatable.setProducts(order.getProducts());
                updatable.setPaymentState(order.getPaymentState());
                updatable.setOrderStatus(order.getOrderStatus());
            }
        }
    }

    @Override
    public void repeatOrder(Long id) {
        if (id != null) {
            Order repeated = findById(id);
            add(repeated);
        }
    }

    @Override
    public List<Order> findAllByUser(Long userId) {
        List<Order> collectOrders = new ArrayList<>();
        if (userId != null) {
            for (Order finded : ORDERS) {
                if (userId.equals(finded.getUser().getId())) {
                    collectOrders.add(finded);
                }
            }
                    }
        return collectOrders;
    }

    @Override
    public List<Order> findAll() {
        return ORDERS;
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        if (id != null & status != null) {
            findById(id).setOrderStatus(status);
        }
    }

    @Override
    public void changePaymentState(Long id, PaymentState state) {
        if (id != null & state != null) {
            findById(id).setPaymentState(state);
        }
    }
}
