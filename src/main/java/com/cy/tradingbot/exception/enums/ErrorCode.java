package com.cy.tradingbot.exception.enums;

import lombok.Builder;

public enum ErrorCode {
    // Common
    NOT_FOUND_ENTITY("C001", "Not Found Entity", 400),
    INVALID_INPUT_VALUE("C001", "Invalid Input Value", 400),
    METHOD_NOT_ALLOWED("C002", "Method Not Allowed", 405),
    INTERNAL_SERVER_ERROR("C004", "Server Error", 500),
    INVALID_TYPE_VALUE("C005", "Invalid Type Value", 400),
    ACCESS_DENIED("C006", "Access is Denied", 403),

    // Login
    EMAIL_DUPLICATION("U001", "Email is Duplication", 400),
    LOGIN_INPUT_INVALID("U002", "Login input is invalid", 400);

    private final String code;
    private final String message;
    private final int responseStatus;

    ErrorCode(String code, String message, int responseStatus) {
        this.code = code;
        this.message = message;
        this.responseStatus = responseStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
