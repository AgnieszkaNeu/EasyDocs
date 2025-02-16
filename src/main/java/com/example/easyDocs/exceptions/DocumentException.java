package com.example.easyDocs.exceptions;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class DocumentException extends RuntimeException{

    public DocumentException(){super("An error occurred while loading the file");}
    public DocumentException(MultipartFile file){
        super("Empty document error.");
    }
    public DocumentException(File dir){
        super("The system cannot find a specific path: " + dir.getAbsolutePath());
    }
    public DocumentException(String name){super("An error occurred while loading the file: " + name);}
    public DocumentException(Long id){super("Could not find document with id: " + id);}
}
