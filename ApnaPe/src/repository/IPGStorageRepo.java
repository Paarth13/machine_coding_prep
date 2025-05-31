package repository;

import models.Client;
import paymodes.PaymentModes;

import java.util.List;

public interface IPGStorageRepo {
    public void addClient(Client bank);
    public void removeClient(Client bank);
    public boolean hasClient(Client bank);
    public List<String> listSupportedPaymodes(Client bank);
    public void addSupportForPaymode(Client bank, PaymentModes paymentModes);
    public void removePaymode(Client bank, PaymentModes paymentModes);
    public void showDistribution();
    public void makePayment();
}
