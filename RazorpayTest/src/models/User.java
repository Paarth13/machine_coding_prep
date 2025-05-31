package models;


public class User {
    String name;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String userID;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }
}
