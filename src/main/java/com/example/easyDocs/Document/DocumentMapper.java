package com.example.easyDocs.Document;

import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDto dicumentToDocumentDto(Document document){
        return  new DocumentDto(
                document.getName(),
                document.getCreator(),
                document.getCreation_date(),
                document.getDescription());
    }
}
