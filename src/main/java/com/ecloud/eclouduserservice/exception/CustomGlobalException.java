package com.ecloud.eclouduserservice.exception;

public class CustomGlobalException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private static final long serialVersionUID = 1L;

    public CustomGlobalException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
