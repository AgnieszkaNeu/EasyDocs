package com.example.easyDocs.exceptions;

public class AccessException extends RuntimeException{

    public AccessException(){ super("You are not authorized to view the document.");}
}
