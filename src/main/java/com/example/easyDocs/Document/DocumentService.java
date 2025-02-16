package com.example.easyDocs.Document;

import com.example.easyDocs.User.User;
import com.example.easyDocs.User.UserRepository;
import com.example.easyDocs.exceptions.DocumentException;
import com.example.easyDocs.exceptions.UserNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentService {

    DocumentMapper documentMapper;
    DocumentRepository documentRepository;
    DocumentStorageService documentStorageService;
    UserRepository userRepository;
    List<String> supported_files = List.of("txt", "doc", "docx", "pdf");

    public DocumentService(DocumentMapper documentMapper, DocumentRepository documentRepository, DocumentStorageService documentStorageService, UserRepository userRepository){
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
        this.documentStorageService = documentStorageService;
        this.userRepository = userRepository;
    }

    public DocumentDto getDocumentById(Long id){
        Document file = documentRepository.findById(id).orElse(new Document());
        return documentMapper.dicumentToDocumentDto(file);
    }

    public List<DocumentDto> getDocumentsByName(String name){
        List<Document> documents = documentRepository.findAllByName(name);
        return documents
                .stream()
                .map(document -> documentMapper.dicumentToDocumentDto(document))
                .collect(Collectors.toList());
    }

    public Resource getDocumentAsResource(Long id){
        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentException(id));
        return documentStorageService.getDocumentAsResource(document.getFile_path());
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
                LocalDate.now(),
                savedFile.getPath()
        );

        documentRepository.save(document);
    }
}
