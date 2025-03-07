package com.example.easyDocs.exceptions;

public class AccessGroupException extends RuntimeException{

    public AccessGroupException(Long id) {super("Could not find group with id: " + id);}
}
