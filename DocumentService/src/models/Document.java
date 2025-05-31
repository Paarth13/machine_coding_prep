package models;

import java.util.HashMap;
import java.util.List;

public class Document {
    HashMap<String,AccessLevel> usersWithAccess;
    String content;

    public Document(HashMap<String, AccessLevel> usersWithAccess, String content, String documentId) {
        this.usersWithAccess = usersWithAccess;
        this.content = content;
        this.documentId = documentId;
    }

    String documentId;

    public HashMap<String, AccessLevel> getUsersWithAccess() {
        return usersWithAccess;
    }

    public void setUsersWithAccess(HashMap<String, AccessLevel> usersWithAccess) {
        this.usersWithAccess = usersWithAccess;
    }

    public void addUsersWithAccess( String userID,AccessLevel accessLevel)
    {
        usersWithAccess.put(userID,accessLevel);
    }

    public String removeUserWithAccess(String userID)
    {
        if(usersWithAccess.containsKey(userID)){
            usersWithAccess.remove(userID);
            return "User ID successfully removed";
        }
        else {
            return "Invalid User ID";
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
