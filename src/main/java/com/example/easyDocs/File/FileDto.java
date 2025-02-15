package com.example.easyDocs.File;


import com.example.easyDocs.User.User;

import java.time.LocalDate;

public record FileDto(String name, User createdBy, LocalDate creation_date, String description) {
}
