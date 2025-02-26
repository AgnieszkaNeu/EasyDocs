package com.example.easyDocs.Document;

import com.example.easyDocs.AccessGroup.AccessGroupRepository;
import com.example.easyDocs.AccessGroup.AccessGroupService;
import com.example.easyDocs.User.User;
import com.example.easyDocs.User.UserRepository;
import com.example.easyDocs.exceptions.AccessException;
import com.example.easyDocs.exceptions.DocumentException;
import com.example.easyDocs.exceptions.UserNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentService {

    DocumentMapper documentMapper;
    DocumentRepository documentRepository;
    DocumentStorageService documentStorageService;
    UserRepository userRepository;
    List<String> supported_files = List.of("txt", "doc", "docx", "pdf");
    AccessGroupService accessGroupService;
    AccessGroupRepository accessGroupRepository;

    public DocumentService(DocumentMapper documentMapper, DocumentRepository documentRepository,
                           DocumentStorageService documentStorageService, UserRepository userRepository,
                           AccessGroupService accessGroupService,
                           AccessGroupRepository accessGroupRepository){


        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
        this.documentStorageService = documentStorageService;
        this.userRepository = userRepository;
        this.accessGroupService = accessGroupService;
        this.accessGroupRepository = accessGroupRepository;
    }

    public User getAuthenticatedUser(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    public List<DocumentDto> getDocuments(Authentication authentication) {

        User user = getAuthenticatedUser(authentication);
        if (Objects.equals(user.getRole(), "ADMIN")) {
            return documentRepository.findAll().stream()
                    .map(document -> documentMapper.documentToDocumentDto(document))
                    .collect(Collectors.toList());
        }
        Set<Document> documents = accessGroupRepository.findAllWithAccess(user.getId());
        return documents.stream()
                .map(document -> documentMapper.documentToDocumentDto(document))
                .collect(Collectors.toList());
    }

    public DocumentDto getDocumentById(Long id, Authentication authentication){

        User user = this.getAuthenticatedUser(authentication);

        if(user.getRole().equals("ADMIN")){
            Document document =  documentRepository.findById(id).orElseThrow(() -> new DocumentException(id));
            return documentMapper.documentToDocumentDto(document);
        }
        return documentMapper.documentToDocumentDto(accessGroupRepository.findByDocumentId(user.getId(),id));
    }

    public List<DocumentDto> getDocumentsByName(String name,Authentication authentication){

        User user = getAuthenticatedUser(authentication);

        Set<Document> documents = documentRepository.findAllByName(name);
        Set<Document> haveAccessDocuments = new HashSet<>();

        for(Document document: documents){
            if(documents.contains(document)|| user.getRole().equals("ADMIN")){
                haveAccessDocuments.add(document);
            }
        }

        return haveAccessDocuments
                .stream()
                .map(document -> documentMapper.documentToDocumentDto(document))
                .collect(Collectors.toList());
    }

    public void deleteDocument(Long id, Authentication authentication) {

        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentException(id));
        User user = getAuthenticatedUser(authentication);

        if(document.getCreator().equals(user) || user.getRole().equals("ADMIN")) {
            documentRepository.delete(document);
        } else {
            throw new AccessException();
        }
    }

    public Resource getDocumentAsResource(Long id, Authentication authentication){

        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentException(id));
        User user = getAuthenticatedUser(authentication);
        Set<Document> documents = accessGroupRepository.findAllWithAccess(user.getId());

        if(documents.contains(document) || user.getRole().equals("ADMIN")) {
            return documentStorageService.getDocumentAsResource(document.getFile_path());
        } else {
            throw new AccessException();
        }
    }

    public List<DocumentDto> getDocumentsByCreator(Long creator_id, Authentication authentication){
        User user = getAuthenticatedUser(authentication);
        User creator = userRepository.findById(creator_id).orElseThrow(() -> new UserNotFoundException(creator_id));

        Set<Document> documents = documentRepository.findAllByCreator(creator);
        Set<Document> haveAccessDocuments = new HashSet<>();
        Set<Document> documentsSharedToUser = accessGroupRepository.findAllWithAccess(user.getId());

        for(Document document: documents){
            if(documentsSharedToUser.contains(document) || user.getRole().equals("ADMIN")){
                haveAccessDocuments.add(document);
            }
        }

        return haveAccessDocuments.stream()
                .map(document -> documentMapper.documentToDocumentDto(document))
                .collect(Collectors.toList());
    }

    public void uploadDocument(MultipartFile multipartFile, Authentication authentication){

        String file_name = multipartFile.getOriginalFilename();
        int lastDotIndex = file_name.lastIndexOf(".");
        String file_type;
        String name;

        System.out.println("File name: " + file_name);

        if (lastDotIndex != -1){
            file_type = file_name.substring(lastDotIndex + 1);
            name = file_name.substring(0,lastDotIndex);
        } else {
            throw new DocumentException();
        }

        if(!supported_files.contains(file_type)) {throw new DocumentException(file_type);}
        File savedFile = documentStorageService.uploadFile(multipartFile);

        User user =  userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()));

        Document document = new Document(
                name,
                file_type,
                user,
                savedFile.getPath()
        );

        documentRepository.save(document);
    }

    public void updateDocument(Long id, Document document, Authentication authentication) {

        User user = getAuthenticatedUser(authentication);
        Document documentToUpdate = documentRepository.findById(id).orElseThrow(() -> new DocumentException(id));

        if (!documentToUpdate.getCreator().equals(user)){
            throw new AccessException();
        }

        boolean documentChanged = false;

        if(document.getName()!=null){
            documentToUpdate.setName(document.getName());
            documentChanged = true;
        }
        if(document.getDescription()!=null){
            documentToUpdate.setDescription(document.getDescription());
            documentChanged = true;
        }
        if(documentChanged){documentToUpdate.setLastUpdate(LocalDate.now());}
        documentRepository.save(documentToUpdate);
    }
}
