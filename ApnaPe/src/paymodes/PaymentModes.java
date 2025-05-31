package paymodes;

import models.PaymentDetails;

public abstract class PaymentModes {
    PaymentDetails paymentDetails;
    String name;
    public PaymentModes(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    public String getName()
    {
        return name;
    }
    public abstract void makePayment();
}
