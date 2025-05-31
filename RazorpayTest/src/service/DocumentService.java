package service;

import models.AccessLevel;
import models.Document;
import models.User;
import repository.IDocumentStorageRepo;

import java.util.HashSet;

public class DocumentService implements IDocumentService{
    IDocumentStorageRepo storageRepo;

    public DocumentService(IDocumentStorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public void createDocument(String userId, Document document) {
     storageRepo.createDocument(userId,document);
    }

    @Override
    public void addAccess(User owner, User userId, Document document, AccessLevel access) {
        storageRepo.addAccess(owner, userId, document, access);
    }

    @Override
    public void modifyDocument(User user, Document document, String content) {
        storageRepo.modifyDocument(user, document, content);
    }

    @Override
    public void getAllDocs(User user) {
        HashSet<Document> docs = storageRepo.getAllDocs(user);
        if(docs.isEmpty())
        {
            System.out.print("No docs present for user\n");
        }
        else
        {
            for(Document document: docs)
            {
                System.out.print(document.getContent());
                System.out.print("\n");
            }
        }
    }

    @Override
    public void deleteDoc(User user, Document doc) {
        storageRepo.deleteDoc(user,doc);
    }
}
