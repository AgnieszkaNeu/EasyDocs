package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.Document.Document;
import com.example.easyDocs.Document.DocumentRepository;
import com.example.easyDocs.User.User;
import com.example.easyDocs.User.UserRepository;
import com.example.easyDocs.exceptions.DocumentException;
import com.example.easyDocs.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class AccessGroupService {

    AccessGroupRepository accessGroupRepository;
    DocumentRepository documentRepository;
    UserRepository userRepository;

    public AccessGroupService(AccessGroupRepository accessGroupRepository, DocumentRepository documentRepository, UserRepository userRepository) {
        this.accessGroupRepository = accessGroupRepository;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public boolean haveAccess(Document document, User user){
        Set<AccessGroup> userGroups = user.getGroups();
        for(AccessGroup accessGroup: userGroups){
            Set<Document> documents = accessGroup.getDocuments();
            for(Document doc: documents){
                if (Objects.equals(doc, document)){
                    return true;
                }
            }
        }
        return false;
    }
}
