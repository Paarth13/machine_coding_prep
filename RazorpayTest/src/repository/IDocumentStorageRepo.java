package repository;

import models.AccessLevel;
import models.Document;
import models.User;

import java.util.HashSet;

public interface IDocumentStorageRepo{
    public void  createDocument(String userId, Document document);
    public void  addAccess(User owner, User userId, Document document, AccessLevel access);
    public void modifyDocument(User user, Document document, String content);
    public HashSet<Document> getAllDocs(User user);
    public void deleteDoc(User user, Document doc);
}
