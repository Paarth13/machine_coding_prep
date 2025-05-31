package service;

import models.Card;
import models.PaymentDetails;
import paymodes.PaymentModes;
import paymodes.PaymodesFactory;
import repository.IPGStorageRepo;

import java.util.List;

public class ApnaPeService {
    IPGStorageRepo ipgStorageRepo;

    public ApnaPeService(IPGStorageRepo ipgStorageRepo) {
        this.ipgStorageRepo = ipgStorageRepo;
    }
    public void addClient(String bankname, PaymentModes paymentModes)
    {

        ipgStorageRepo.addClient(bankname);
    }
    public void removeClient(String bankname)
    {
        ipgStorageRepo.removeClient(bankname);
    }
    public void hasClient(String bankName)
    {
        System.out.println(ipgStorageRepo.hasClient(bankName) ? "Exists" : "Not Present");
    }
//    public List<String> listSupportedPaymodes(String bankName);
//    public void addSupportForPaymode(String bankName, PaymentModes paymentModes);
//    public void removePaymode(String bankName, PaymentModes paymentModes);
//    public void showDistribution();
//    public void makePayment();
}
