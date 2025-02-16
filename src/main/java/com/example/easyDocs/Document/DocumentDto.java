package com.example.easyDocs.Document;


import com.example.easyDocs.User.User;

import java.time.LocalDate;

public record DocumentDto(String name, User createdBy, LocalDate creation_date, String description) {
}
