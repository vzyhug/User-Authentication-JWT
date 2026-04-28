package com.example.day1_connectdb.exception;

public enum APIErrorCode {
    USER_EXISTED(1001, "User already exists"),
    USERNAME_INVALID(1002, "Username is invalid,Username must be at least 2 characters"),
    PASSWORD_INVALID(1003, "Password is invalid,Password must be at least 2 characters"),
    KEY_INVALID(1004, "Message is invalid"),
    NOTFOUND(1005,"User not found"),
    USER_NOT_FOUND(1006,"User Login failed"),
    UNAUTHENTICATED(1007,"Authentication failed"),
    ;

    APIErrorCode(int success, String message) {
        this.success = success;
        this.message = message;
    }

    private int success = 1000 ;
    private String message;
    // Getters and Setters

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
