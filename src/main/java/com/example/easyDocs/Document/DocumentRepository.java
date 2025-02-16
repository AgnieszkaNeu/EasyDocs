package com.example.easyDocs.Document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Optional<Document> findByName(String name);
    List<Document> findAllByName(String name);
}
