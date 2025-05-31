package repository;

import models.AccessLevel;
import models.Document;
import models.User;

import javax.print.Doc;
import java.util.HashMap;
import java.util.HashSet;

public class DocumentStorageRepo implements IDocumentStorageRepo {

    HashMap<String, HashSet<Document>> userDocumentHashMap;

    public DocumentStorageRepo() {
        this.userDocumentHashMap = new HashMap<>();
    }

    private  Boolean checkIfDocumentExists(String userID, Document document)
    {
        if(userDocumentHashMap.containsKey(userID) && userDocumentHashMap.get(userID).contains(document))
        {
            return true;
        }
        return  false;
    }

    @Override
    public void createDocument(String userId, Document document) {
        if(!checkIfDocumentExists(userId,document))
        {
            if(userDocumentHashMap.containsKey(userId)) {
                userDocumentHashMap.get(userId).add(document);
            }
            else {
                HashSet<Document> doc = new HashSet<>();
                doc.add(document);
                userDocumentHashMap.put(userId,doc);
            }
        }
        else
        {System.out.print("Doc already exists \n");}
        return;
    }

    @Override
    public void addAccess(User owner, User userId, Document document, AccessLevel access) {
        if(!checkIfCanAddAccess(owner,document)) {
            return;
        }
        document.addUser(userId,access);
        if(userDocumentHashMap.containsKey(userId.getUserID())) {
            userDocumentHashMap.get(userId.getUserID()).add(document);
        }
    }

    public void modifyDocument(User user, Document document, String content){
        if(document.getCreator().equals(user) ||
                document.getUserAccessLevelMap().containsKey(user) && document.getUserAccessLevelMap().get(user).compareTo(AccessLevel.WRITE) == 0)
        {
            document.setContent(content);
        }
        else
        {
            System.out.print(user.getName() + " doesn't have access to modify\n");
        }
    }

    @Override
    public HashSet<Document> getAllDocs(User user) {
        if(userDocumentHashMap.containsKey(user.getUserID())) {
            return userDocumentHashMap.get(user.getUserID());
        }
        System.out.print("User Doesn't exist");
        return null;
    }

    private Boolean checkIfCanAddAccess(User owner, Document document){
        if(owner.getUserID().equals(document.getCreator().getUserID())){
            return true;
        }
        System.out.print(owner.getName()+ " doesn't have access to modify\n");
        return  false;
    }

    public void setUserDocumentHashMap(HashMap<String, HashSet<Document>> userDocumentHashMap) {
        this.userDocumentHashMap = userDocumentHashMap;
    }

    public void deleteDoc(User user, Document doc)
    {
        if(checkIfDocumentExists(user.getUserID(),doc)) {
            userDocumentHashMap.get(user.getUserID()).remove(doc);
        }
    }
}
