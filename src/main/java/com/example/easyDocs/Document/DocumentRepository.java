package com.example.easyDocs.Document;

import com.example.easyDocs.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Optional<Document> findByName(String name);
    Set<Document> findAllByName(String name);
    Set<Document> findAllByCreator(User creator);
}
