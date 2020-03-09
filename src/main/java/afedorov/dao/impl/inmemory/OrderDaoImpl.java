package afedorov.dao.impl.inmemory;

import afedorov.dao.OrderDao;
import afedorov.entities.Order;
import afedorov.entities.OrderStatus;
import afedorov.entities.PaymentState;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Long idGenerator = 1l;
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        if (order != null) {
            order.setId(idGenerator);
            idGenerator++;
            orders.add(order);
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            for (Order finded : orders) {
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
    public List<Order> findAllByClient(Long clientId) {
        if (clientId != null) {
            List<Order> collectOrders = new ArrayList<>();
            for (Order finded : orders) {
                if (clientId.equals(finded.getClient().getId())) {
                    collectOrders.add(finded);
                }
                return collectOrders;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orders;
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