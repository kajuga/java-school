package afedorov.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Order {
    private Long id;
    private User user;
    private Address address;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private Map<ProductInCart, Integer> products;
    private PaymentState paymentState;
    private OrderStatus orderStatus;
    private BigDecimal orderCost;
}
