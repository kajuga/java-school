package afedorov.entities;

public enum PaymentMethod {

    CASH,
    CARD_PAYMENT;

    public static PaymentMethod fromKey(String key){
        for (PaymentMethod paymentMethod: values()){
           if (paymentMethod.name().equals(key)){
               return paymentMethod;
           }
        }
        throw new IllegalArgumentException("Incorrect PaymentMethod");
    }

}
