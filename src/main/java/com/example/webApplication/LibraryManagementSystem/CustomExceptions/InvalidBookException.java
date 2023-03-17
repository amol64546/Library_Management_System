package com.example.webApplication.LibraryManagementSystem.CustomExceptions;

public class InvalidBookException extends Exception{
    public InvalidBookException(String msg){
        super(msg);
    }
}
