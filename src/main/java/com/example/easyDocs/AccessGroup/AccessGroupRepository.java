package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.Document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {
    Set<Document> findAllByDocuments(Set<Document> documents);
}
