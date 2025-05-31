package models;

public class Card {
    String number;
    String expiry;
    int cvv;

    public Card(String number, String expiry, int cvv) {
        this.number = number;
        this.expiry = expiry;
        this.cvv = cvv;
    }
}
