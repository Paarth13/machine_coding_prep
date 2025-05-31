package service;

import models.AccessLevel;
import models.Document;
import models.User;

public interface IDocumentService {
    public void  createDocument(String userId, Document document);
    public void  addAccess(User owner, User userId, Document document, AccessLevel access);
    public void modifyDocument(User user, Document document, String content);
    public void getAllDocs(User user);
    public void deleteDoc(User user, Document doc);
}
