package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.Document.Document;
import com.example.easyDocs.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AccessGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "document_group_access",
            joinColumns = @JoinColumn(name = "group_access_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    @JsonIgnore
    Set<Document> documents = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_group_access",
            joinColumns = @JoinColumn(name = "group_access_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    User founder;

    public AccessGroup(Long id, String name, Set<Document> documents, Set<User> users, User founder) {
        this.id = id;
        this.name = name;
        this.documents = documents;
        this.users = users;
        this.founder = founder;
    }

    public AccessGroup() {

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

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User initiator) {
        this.founder = initiator;
    }
}
