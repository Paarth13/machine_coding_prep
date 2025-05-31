package paymodes;

import models.Card;
import models.NetBankingDetails;
import models.PaymentDetails;

public class PaymodesFactory {
    public static PaymentModes getPaymode(PaymentDetails paymentDetails)
    {
        return switch (name) {
            case "CC" -> new CreditCardPayMode(paymentDetails);
            case "NetBanking" -> new NetBankingPayMode(paymentDetails);
            default -> new UpiPaymentMode(paymentDetails);
        };
    }
}
