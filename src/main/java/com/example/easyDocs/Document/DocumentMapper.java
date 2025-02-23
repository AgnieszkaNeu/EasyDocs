package com.example.easyDocs.Document;

import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDto documentToDocumentDto(Document document){
        String author_name = document.getCreator().getFirst_name() + " " + document.getCreator().getLast_name();

        return  new DocumentDto(
                document.getId(),
                document.getName(),
                author_name,
                document.getCreator().getEmail(),
                document.getCreation_date(),
                document.getDescription());
    }
}
