package repository;

import models.AccessLevel;
import models.Document;
import models.User;

import java.util.HashMap;

public class DocumentRepository implements IDocumentRepository{
    HashMap<String, Document> documentHashMap;
    HashMap<String, User> userHashMap;
    int n;

    public DocumentRepository() {
        this.documentHashMap = new HashMap<>();
        this.userHashMap = new HashMap<>();
        this.n =0;
    }

    @Override
    public Boolean addAccessToDocument(String DocumentId, String UserID, AccessLevel accessLevel) {
        if(!documentHashMap.containsKey(DocumentId))
            return Boolean.FALSE;
        documentHashMap.get(DocumentId).addUsersWithAccess(UserID,accessLevel);
        return Boolean.TRUE;
    }

    @Override
    public String readDocument(String UserID, String documentID) {
        if(!documentHashMap.containsKey(documentID))
            return "Document Doesn't exist\n";
        if(!documentHashMap.get(documentID).getUsersWithAccess().containsKey(UserID))
            return "No access available";
        return documentHashMap.get(documentID).getContent();
    }

    @Override
    public String editDocument(String UserID, String documentID, String content) {
        if(!documentHashMap.containsKey(documentID))
            return "Document Doesn't exist\n";
        if(!documentHashMap.get(documentID).getUsersWithAccess().containsKey(UserID))
            return "No access available";
        if(documentHashMap.get(documentID).getUsersWithAccess().get(UserID).compareTo(AccessLevel.EDIT)< 0)
            return "No edit Access";
        documentHashMap.get(documentID).setContent(content);
        return "Successful Edit";
    }

    @Override
    public Boolean createDocument(String UserID, String content) {
        String documentID = "document_"+ n;
        documentHashMap.put(documentID, new Document({UserID,AccessLevel.DELETE},content,documentID))
        return Boolean.TRUE;
    }
}
