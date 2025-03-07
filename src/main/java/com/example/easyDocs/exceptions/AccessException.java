package com.example.easyDocs.exceptions;

public class AccessException extends RuntimeException{

    public AccessException(){ super("You do not have permission to perform the action");}
}
