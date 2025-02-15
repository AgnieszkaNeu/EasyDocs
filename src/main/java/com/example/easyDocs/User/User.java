package com.example.easyDocs.User;

import com.example.easyDocs.File.File;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String first_name;

    @Column(nullable = false)
    String last_name;

    @Column(nullable = false)
    String password;

    String role = "USER";

    String job_title;

    String phone_number;

    String description;

    @Column(nullable = false)
    LocalDate user_creation_date;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    List<File> files = new ArrayList<>();

    public User() {
    }

    public User(String email, String first_name, String last_name, String password,
                String job_title, LocalDate created_time,String phone_number) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.job_title = job_title;
        this.user_creation_date = created_time;
        this.phone_number = phone_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public LocalDate getUser_creation_date() {
        return user_creation_date;
    }

    public void setUser_creation_date(LocalDate created_time) {
        this.user_creation_date = created_time;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
