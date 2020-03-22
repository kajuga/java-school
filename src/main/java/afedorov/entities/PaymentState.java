package afedorov.entities;

public enum PaymentState {

    AWAITING_PAYMENT,
    BEING_PROCESSED,
    COMPLETED,
    CANCELED,
    FROZEN,
    ERROR;

    public static PaymentState fromKey(String key){
        for (PaymentState paymentState : values()){
            if (paymentState.name().equals(key)){
                return paymentState;
            }
        }
        throw new IllegalArgumentException("Incorrect paymentState");
    }

}
