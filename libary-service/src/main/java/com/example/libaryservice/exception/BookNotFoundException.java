package com.example.libaryservice.exception;

public class BookNotFoundException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }


    public BookNotFoundException(ExceptionMessage exceptionMessage , String message) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }


}
