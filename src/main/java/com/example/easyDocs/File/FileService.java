package com.example.easyDocs.File;

public class FileService {

    FileMapper fileMapper;
    FileRepository fileRepository;

    public FileService(FileMapper fileMapper, FileRepository fileRepository){
        this.fileMapper = fileMapper;
        this.fileRepository = fileRepository;
    }

    public FileDto returnFileById(Long id){
        File file = fileRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return fileMapper.fileToFileDto(file);
    }

    public FileDto returnFileByName(String name){
        File file = fileRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        return fileMapper.fileToFileDto(file);
    }
}
