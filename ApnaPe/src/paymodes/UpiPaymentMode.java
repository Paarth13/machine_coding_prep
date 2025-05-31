package paymodes;

import models.PaymentDetails;

public class UpiPaymentMode extends PaymentModes{
    public UpiPaymentMode(PaymentDetails paymentDetails) {
        super(paymentDetails);
        super.name = "UPI";
    }

    @Override
    public void makePayment() {
        paymentDetails.getUpiId();
    }
}
