package com.example.easyDocs.Document;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class DocumentController {

    DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }


    @GetMapping("/documentDto/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id){
        DocumentDto document = documentService.getDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    @GetMapping("/documentDto")
    public ResponseEntity<List<DocumentDto>> getDocumentsByName(@RequestParam("documentName") String documentName){
        List<DocumentDto> documents = documentService.getDocumentsByName(documentName);
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }
/*
    @GetMapping("/file")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCreator(@RequestParam("user_name") String name){
        List<DocumentDto> documents = documentService.getDocumentsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }*/

    @GetMapping("/document/{id}")
    public ResponseEntity<Resource> getDocumentAsResource(@PathVariable Long id){
        Resource resource = documentService.getDocumentAsResource(id);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/file")
    public ResponseEntity<HttpStatus> uploadFile(@RequestParam("file")MultipartFile file, Authentication authentication){
        documentService.uploadDocument(file, authentication);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
