package repository;

import models.AccessLevel;

public interface IDocumentRepository {
    public Boolean  addAccessToDocument(String DocumentId, String UserID, AccessLevel accessLevel);
    public String readDocument(String UserID, String documentID);
    public String editDocument(String UserID, String documentID,String content);
    public Boolean createDocument(String UserID, String content);
}
