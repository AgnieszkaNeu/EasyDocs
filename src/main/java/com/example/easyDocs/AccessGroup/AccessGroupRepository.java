package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.Document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AccessGroupRepository extends JpaRepository<AccessGroup,Long> {

    @Query(value = "SELECT d.* FROM document d " +
            "JOIN document_group_access dga ON d.id = dga.document_id " +
            "JOIN user_group_access uga ON dga.group_access_id = uga.group_access_id " +
            "WHERE uga.user_id = :id",
            nativeQuery = true)
    Set<Document> findAllWithAccess(@Param("id") Long id);

    @Query(value = "SELECT d.* FROM document d " +
            "JOIN document_group_access dga ON d.id = dga.document_id "+
            "JOIN user_group_access uga ON dga.group_access_id = uga.group_access_id " +
            "WHERE uga.user_id = :user_id AND dga.document_id = :document_id",
            nativeQuery = true)
    Document findByDocumentId(@Param("user_id") Long user_id,@Param("document_id") Long document_id);
}
