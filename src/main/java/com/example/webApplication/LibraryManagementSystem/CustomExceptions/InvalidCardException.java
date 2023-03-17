package com.example.webApplication.LibraryManagementSystem.CustomExceptions;

public class InvalidCardException extends Exception{
    public InvalidCardException(String msg){
        super(msg);
    }
}
