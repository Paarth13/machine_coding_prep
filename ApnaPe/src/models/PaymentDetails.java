package models;

public class PaymentDetails {
    Card card;
    NetBankingDetails netBankingDetails;
    String upiId;

    public PaymentDetails(String upiId) {
        this.upiId = upiId;
    }

    public PaymentDetails(NetBankingDetails netBankingDetails) {
        this.netBankingDetails = netBankingDetails;
    }

    public PaymentDetails(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public NetBankingDetails getNetBankingDetails() {
        return netBankingDetails;
    }

    public void setNetBankingDetails(NetBankingDetails netBankingDetails) {
        this.netBankingDetails = netBankingDetails;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
}
