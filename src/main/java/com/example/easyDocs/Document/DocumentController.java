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

    @GetMapping("/document")
    public ResponseEntity<List<DocumentDto>>getDocuments(
            @RequestParam(value = "documentName", required = false, defaultValue = "") String documentName){

        List<DocumentDto> documents;

        if(documentName.isBlank()) {
            documents = documentService.getDocuments();
        } else {
            documents = documentService.getDocumentsByName(documentName);
        }
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id){
        DocumentDto document = documentService.getDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    @PostMapping("/document")
    public ResponseEntity<HttpStatus> uploadDocument(@RequestParam("file")MultipartFile file, Authentication authentication){
        documentService.uploadDocument(file, authentication);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/document/{id}")
    public ResponseEntity<HttpStatus> updateDocument(@PathVariable Long id, @RequestBody Document document){
        documentService.updateDocument(id,document);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/documentByCreator")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCreator(@RequestParam("user_id") Long creator_id){
        List<DocumentDto> documents = documentService.getDocumentsByCreator(creator_id);
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @GetMapping("/documentResource/{id}")
    public ResponseEntity<Resource> getDocumentAsResource(@PathVariable Long id){
        Resource resource = documentService.getDocumentAsResource(id);
        return ResponseEntity.ok(resource);
    }
}
