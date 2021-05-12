package com.cy.tradingbot.exception;

import com.cy.tradingbot.exception.enums.ErrorCode;

public class LoginInputInvalidException extends BusinessException {
    public LoginInputInvalidException() {
        super(ErrorCode.LOGIN_INPUT_INVALID);
    }
}
