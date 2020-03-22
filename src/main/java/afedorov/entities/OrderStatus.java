package afedorov.entities;

public enum OrderStatus {

    CREATED,
    APPROVED,
    AWAITING_PAYMENT,
    AWAITING_SHIPMENT,
    SHIPPED,
    DELIVERED;


    public static OrderStatus fromKey(String key){
        for (OrderStatus orderStatus: values()){
            if (orderStatus.name().equals(key)){
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Incorrect orderStatus");
    }

}
