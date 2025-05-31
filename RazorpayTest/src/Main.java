import models.AccessLevel;
import models.Document;
import models.User;
import repository.DocumentStorageRepo;
import repository.IDocumentStorageRepo;
import repository.IUserRepository;
import repository.InMemUserRepository;
import service.DocumentService;
import service.IDocumentService;
import service.IUserService;
import service.UserService;

/**
 * Implement a Simple document Service:
 * - A document has string content.
 * - All documents are private when created only the user who creates the document has access for reading/editing the document by default.
 * - Only the owners of documents can grant {read/edit} access to other users. No user should be able to grant access for a document if he is not the creator of the document.
 * - Only the owner can delete a document.
 * - The Document can be stored in 2 types of storage: hot and cold storage, hot storage can be in memory and cold can be a file system. The document can be moved between hot and cold storage
 */

public class Main {
    public static void main(String[] args) {
        IDocumentStorageRepo documentStorageRepo = new DocumentStorageRepo();
        IDocumentService documentService = new DocumentService(documentStorageRepo);
        IUserRepository userRepository = new InMemUserRepository();
        IUserService userService = new UserService(userRepository);

        User user1 = new User("Paarth","user1");
        userService.addUser(user1);
        Document doc = new Document("doc1",user1,"This is Paarth here");
        documentService.createDocument(user1.getUserID(),doc);
        User user2 = new User("John","user2");
        userService.addUser(user2);
        Document doc2 = new Document("doc2",user1,"This is John here");
        documentService.getAllDocs(user1);
        documentService.modifyDocument(user2,doc,"John changed paarth's content");
        documentService.getAllDocs(user1);
        documentService.addAccess(user1,user2,doc, AccessLevel.WRITE);
        documentService.modifyDocument(user2,doc,"John changed paarth's content");
        documentService.getAllDocs(user1);
        documentService.deleteDoc(user1,doc);
//        documentService.getAllDocs(user1);
        User user3 = new User("Joe","user3");
        userService.addUser(user3);
        documentService.addAccess(user1,user2,doc,AccessLevel.READ);

    }
}