package com.example.easyDocs.Document;

import com.example.easyDocs.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String file_type;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    User creator;

    LocalDate creation_date;

    @Column(nullable = false)
    String file_path;

    String description;

    public Document() {
    }

    public Document(String name, String fileType, User creator, LocalDate creation_date, String file_path, String description) {
        this.name = name;
        this.file_type = fileType;
        this.creator = creator;
        this.creation_date = creation_date;
        this.file_path = file_path;
        this.description = description;
    }

    public Document(String name, String fileType, User creator, LocalDate creation_date, String file_path) {
        this.name = name;
        this.file_type = fileType;
        this.creator = creator;
        this.creation_date = creation_date;
        this.file_path = file_path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String fileType) {
        this.file_type = fileType;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator(){
        return this.creator;
    }

    public void setCreator(User createdBy) {
        this.creator = createdBy;
    }
}
