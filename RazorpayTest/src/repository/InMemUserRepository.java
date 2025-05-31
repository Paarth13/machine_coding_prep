package repository;

import models.Document;
import models.User;

import java.util.HashMap;
import java.util.HashSet;

public class InMemUserRepository implements IUserRepository{

    String userId;
    HashMap<String, User> userHashMap;
    Integer userCount;
    public InMemUserRepository() {
        this.userId = "";
        this.userCount = 0;
        this.userHashMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        userHashMap.put(userId, user);
    }
}
