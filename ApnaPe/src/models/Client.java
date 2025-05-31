package models;

import java.util.List;

public class Client {
    String name;
    int id;
    List<String> banks;

    public Client(String name, int id, List<String> banks) {
        this.name = name;
        this.id = id;
        this.banks = banks;
    }
}
