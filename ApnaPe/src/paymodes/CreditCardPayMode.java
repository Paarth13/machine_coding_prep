package paymodes;

import models.PaymentDetails;

public class CreditCardPayMode extends PaymentModes{
    public CreditCardPayMode(PaymentDetails paymentDetails) {
        super(paymentDetails);
        super.name = "Credit Card";
    }

    @Override
    public void makePayment() {
        System.out.println("Make Credit card payment");
    }
}
