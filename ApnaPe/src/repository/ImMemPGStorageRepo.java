package repository;

import models.Client;
import paymodes.PaymentModes;
import paymodes.PaymodesFactory;

import java.util.*;

public class ImMemPGStorageRepo implements IPGStorageRepo{
    HashMap<Client,HashMap<String, HashSet<PaymentModes>>> bankPaymentModes;

    @Override
    public void addClient(Client bank) {
        bankPaymentModes.put(bank,new HashMap<>());
    }

    @Override
    public void removeClient(Client bank) {
        bankPaymentModes.remove(bank);
    }

    @Override
    public boolean hasClient(Client bank) {
        return bankPaymentModes.containsKey(bank);
    }

    @Override
    public List<String> listSupportedPaymodes(Client client) {
        HashSet<String> names =  new HashSet<>();
        for(Map.Entry<String,HashSet<PaymentModes>> bank: bankPaymentModes.get(bank))
        {
            for(PaymentModes pm : bank.getValue())
            {
                names.add(pm.getName());
            }
        }
        return names.stream().toList();
    }

    @Override
    public void addSupportForPaymode(Client bankName, PaymentModes mode) {
        if(bankPaymentModes.containsKey(bankName))
        {
            bankPaymentModes.get(bankName).get(bankName).add(mode);
        }
        else{
            bankPaymentModes.put(bankName,new HashSet<>(Set.of(mode)));
        }
    }

    @Override
    public void removePaymode(String bankName, PaymentModes paymentModes) {
        if(bankPaymentModes.containsKey(bankName))
        {
            bankPaymentModes.get(bankName).remove(paymentModes);
        }
    }

    @Override
    public void showDistribution() {

    }

    @Override
    public void makePayment() {

    }
}
