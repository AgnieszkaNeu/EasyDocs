package com.example.easyDocs.Document;

import com.example.easyDocs.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Optional<Document> findByName(String name);
    List<Document> findAllByName(String name);
    List<Document> findAllByCreator(User creator);
}
