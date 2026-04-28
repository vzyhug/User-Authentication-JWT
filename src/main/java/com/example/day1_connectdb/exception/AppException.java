package com.example.day1_connectdb.exception;

public class AppException extends RuntimeException {
    private APIErrorCode errorCode;

    public AppException(APIErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public APIErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(APIErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
