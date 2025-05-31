package paymodes;

import models.PaymentDetails;

public class NetBankingPayMode extends PaymentModes{
    public NetBankingPayMode(PaymentDetails paymentDetails) {
        super(paymentDetails);
        super.name = "NetBanking";
    }

    @Override
    public void makePayment() {

    }
}
