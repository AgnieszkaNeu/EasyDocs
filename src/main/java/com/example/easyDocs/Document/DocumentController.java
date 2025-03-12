package com.example.easyDocs.Document;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping("/document")
public class DocumentController {

    DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }


    @GetMapping("")
    public ResponseEntity<List<DocumentDto>>getDocuments(
            @RequestParam(value = "documentName", required = false, defaultValue = "") String documentName){

        List<DocumentDto> documents;

        if(documentName.isBlank()){
            documents = documentService.getDocuments();
        } else {
            documents = documentService.getDocumentsByName(documentName);
        }
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id){
        DocumentDto document = documentService.getDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> uploadDocument(@RequestParam("file")MultipartFile file, Authentication authentication){
        documentService.uploadDocument(file, authentication);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable Long id, Authentication authentication){
        documentService.deleteDocument(id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateDocument(@PathVariable Long id, @RequestBody Document document, Authentication authentication){
        documentService.updateDocument(id,document,authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/documentByCreator/{id}")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCreator(@PathVariable Long id, Authentication authentication){
        List<DocumentDto> documents = documentService.getDocumentsByCreator(id,authentication);
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<Resource> getDocumentAsResource(@PathVariable Long id, Authentication authentication){
        Resource resource = documentService.getDocumentAsResource(id, authentication);
        return ResponseEntity.ok(resource);
    }
}
