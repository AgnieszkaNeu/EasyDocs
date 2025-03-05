package com.example.easyDocs.Document;

import java.time.LocalDate;

public record DocumentDto(Long doc_id, String name, String createdBy, String creatorEmail,
                          LocalDate creation_date, String description) {
}
