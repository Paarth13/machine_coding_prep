package models;

import java.util.HashMap;
import java.util.HashSet;

public class Document {
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public HashMap<User, AccessLevel> getUserAccessLevelMap() {
        return userAccessLevelMap;
    }

    public void setUserAccessLevelMap(HashMap<User, AccessLevel> userAccessLevelMap) {
        this.userAccessLevelMap = userAccessLevelMap;
    }

    public void addUser(User user, AccessLevel accessLevel)
    {
        this.userAccessLevelMap.put(user,accessLevel);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    String documentId;
    HashMap<User,AccessLevel> userAccessLevelMap;
    String content;
    User creator;

    public Document(String documentId, User creator, String content) {
        this.documentId = documentId;
        this.userAccessLevelMap = new HashMap<>();
        this.creator = creator;
        this.content = content;
    }
}
