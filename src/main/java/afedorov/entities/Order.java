package afedorov.entities;

import lombok.Data;

import java.util.Map;

@Data
public class Order {
    private Long id;
    private User user;
    private Address address;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private Map<Product, Integer> products;
    private PaymentState paymentState;
    private OrderStatus orderStatus;
}
