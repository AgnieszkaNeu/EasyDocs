package com.example.easyDocs.Document;

import com.example.easyDocs.exceptions.DocumentException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class DocumentStorageService {

    private static final Path UPLOAD_DIR = Path.of(System.getProperty("user.home") + "/uploads/");

    public File uploadFile(MultipartFile file){

        File uploadDir = new File(String.valueOf(UPLOAD_DIR));

        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new DocumentException(uploadDir);
            }
        }

        if(file.isEmpty()){
            throw new DocumentException(file);
        }

        try{File savedFile = new File(UPLOAD_DIR + file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource getDocumentAsResource(String documentPath){

        Resource resource = new FileSystemResource(documentPath);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        else {
            throw new RuntimeException();

        }

    }
}
