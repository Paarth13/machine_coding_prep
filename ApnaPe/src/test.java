package com.practice.one;

import java.util.*;
import java.util.stream.Collectors;

public class PaymentGatewaySystem {

    public enum PaymentMethod {
        NB, CARD, UPI
    }

    public enum PaymentStatus {
        FAILED, PENDING, SUCCESSFUL
    }

    public static abstract class Bank {

        public Set<PaymentMethod> supportedMethods;

        public Bank(Set<PaymentMethod> supportedMethods) {
            this.supportedMethods = supportedMethods;
        }

        public boolean processPayment(Payment payment) {
            return true;
        }

        public boolean cancelPayment(Payment payment) {
            return true;
        }

        public abstract double getSuccessRate(PaymentMethod paymentMethod);

        public boolean supports(PaymentMethod paymentMethod) {
            return supportedMethods.contains(paymentMethod);
        }
    }

    public static class HDFC extends Bank {

        public HDFC(Set<PaymentMethod> supportedMethods) {
            super(supportedMethods);
        }

        @Override
        public double getSuccessRate(PaymentMethod paymentMethod) {
            return 0.7;
        }
    }

    public static class SBI extends Bank {

        public SBI(Set<PaymentMethod> supportedMethods) {
            super(supportedMethods);
        }

        @Override
        public double getSuccessRate(PaymentMethod paymentMethod) {
            return 0.6;
        }
    }

    public static abstract class PaymentStrategy {
        PaymentMethod paymentMethod;

        public PaymentStrategy(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public Bank getBank() {
            return BankRouter.getInstance().getBankForPaymentMethod(paymentMethod);
        }
    }

    public static class BankRouter {
        List<Bank> banks;

        static BankRouter instance;

        public static BankRouter getInstance() {
            if (instance == null) {
                instance = new BankRouter();
            }
            return instance;
        }

        public BankRouter() {
            HDFC hdfc = new HDFC(new HashSet<>(Arrays.asList(PaymentMethod.UPI, PaymentMethod.NB)));
            SBI sbi = new SBI(new HashSet<>(Arrays.asList(PaymentMethod.UPI, PaymentMethod.CARD)));
            this.banks = Arrays.asList(hdfc, sbi);
        }

        public Bank getBankForPaymentMethod(PaymentMethod paymentMethod) {
            double successRate = 0;
            Bank selectedBank = banks.get(0);
            List<Bank> eligibleBanks = banks.stream().filter(b -> b.supports(paymentMethod)).collect(Collectors.toList());
            for (Bank bank : eligibleBanks) {
                if (successRate < bank.getSuccessRate(paymentMethod)) {
                    successRate = bank.getSuccessRate(paymentMethod);
                    selectedBank = bank;
                }
            }
            return selectedBank;
        }
    }

    public static class Client {
        int id;
        String name;
        Set<PaymentMethod> supportedMethods;

        public Client(int id, String name, Set<PaymentMethod> paymentMethods) {
            this.id = id;
            this.name = name;
            this.supportedMethods = paymentMethods;
        }

        public Set<PaymentMethod> listSupportedPayMethod() {
            return this.supportedMethods;
        }

        public void addNewPaymentMethod(PaymentMethod method) {
            this.supportedMethods.add(method);
        }

        public void removePaymentMethod(PaymentMethod method) {
            this.supportedMethods.remove(method);
        }

        public boolean supports(PaymentMethod method) {
            return supportedMethods.contains(method);
        }
    }

    public static class ClientStore {
        Map<Integer, Client> clientMap;
        static ClientStore instance;

        private ClientStore() {
            clientMap = new HashMap<>();
        }

        public static ClientStore getInstance() {
            if (instance == null) {
                instance = new ClientStore();
            }
            return instance;
        }

        public Client getClient(int id) {
            return clientMap.get(id);
        }

        public void addClient(Client client) {
            instance.clientMap.put(client.id, client);
        }
    }

    public static class PaymentStore {
        Map<String, Payment> paymentMap;
        static PaymentStore instance;

        private PaymentStore() {
            paymentMap = new HashMap<>();
        }

        public static PaymentStore getInstance() {
            if (instance == null) {
                instance = new PaymentStore();
            }
            return instance;
        }

        public Payment getPayment(String id) {
            return paymentMap.get(id);
        }

        public void addPayment(Payment payment) {
            paymentMap.put(payment.paymentId, payment);
        }
    }

    public static class PaymentGateway {
        ClientStore clientStore;
        PaymentStore paymentStore;

        public PaymentGateway() {
            this.clientStore = ClientStore.getInstance();
            this.paymentStore = PaymentStore.getInstance();
        }

        public void addClient(Client client) {
            clientStore.addClient(client);
        }

        public void addNewPaymentMethod(int clientId, PaymentMethod method) {
            if (clientStore.getClient(clientId) != null) {
                Client client = clientStore.getClient(clientId);
                client.addNewPaymentMethod(method);
            }
        }

        public void removePaymentMethod(int clientId, PaymentMethod method) {
            if (clientStore.getClient(clientId) != null) {
                Client client = clientStore.getClient(clientId);
                client.removePaymentMethod(method);
            }
        }

        public Payment processPayment(int clientId, PaymentStrategy strategy, Double price) {
            Client client = clientStore.getClient(clientId);
            if (client.supports(strategy.paymentMethod)) {
                Payment payment = new Payment(clientId, strategy, price);
                paymentStore.addPayment(payment);
                payment.processPayment();
                System.out.println("payment processed of amount: " + price);
                return payment;
            } else {
                System.out.println("payment method not supported by the client" + strategy.paymentMethod);
                throw new RuntimeException();
            }
        }

        public List<PaymentMethod> supportedMethods(int clientId) {
            Client client = clientStore.getClient(clientId);
            if (client != null) {
                return new ArrayList<>(client.listSupportedPayMethod());
            } else {
                throw new RuntimeException();
            }
        }

        public boolean cancelPayment(String paymentId) {
            Payment payment = paymentStore.getPayment(paymentId);
            if (payment != null) {
                return payment.cancelPayment();
            }
            return false;
        }
    }

    public static class Payment {
        int clientId;
        double price;
        PaymentStrategy paymentStrategy;
        String paymentId;
        PaymentStatus status;
        Bank bank;

        public Payment(int clientId, PaymentStrategy strategy, double price) {
            this.clientId = clientId;
            this.price = price;
            this.paymentStrategy = strategy;
            this.bank = strategy.getBank();
            this.paymentId = UUID.randomUUID().toString();
            this.status = PaymentStatus.PENDING;
        }

        public void processPayment() {
            boolean processed = this.bank.processPayment(this);
            if (processed) {
                status = PaymentStatus.SUCCESSFUL;
            } else {
                status = PaymentStatus.FAILED;
            }
        }

        public boolean cancelPayment() {
            return this.bank.cancelPayment(this);
        }
    }

    public static class UPIPayment extends PaymentStrategy {
        String vpa;
        String number;

        public UPIPayment(String vpa, String number) {
            super(PaymentMethod.UPI);
            this.vpa = vpa;
            this.number = number;
        }
    }

    public static void main(String[] args) {
        Client client = new Client(1, "client1", new HashSet<>(Arrays.asList(PaymentMethod.UPI, PaymentMethod.NB)));
        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.addClient(client);
        UPIPayment upi = new UPIPayment("vpa", "number");
        paymentGateway.processPayment(1, upi, 100.0);

        System.out.println("supported method for the client 1: " + paymentGateway.supportedMethods(1));
    }

}
