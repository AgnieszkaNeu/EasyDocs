package com.example.easyDocs.File;

import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileDto fileToFileDto(File file){
        return  new FileDto(
                file.getName(),
                file.getCreator(),
                file.getCreation_date(),
                file.getDescription());
    }
}
