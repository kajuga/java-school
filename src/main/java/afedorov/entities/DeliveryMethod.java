package afedorov.entities;

public enum DeliveryMethod {
    POST,
    PICKUP,
    COURIER;

    public static DeliveryMethod fromKey(String key){
        for (DeliveryMethod deliveryMethod: values()){
            if (deliveryMethod.name().equals(key)){
                return deliveryMethod;
            }
        }
        throw new IllegalArgumentException("Incorrect deliveryMethod");
    }


    }
