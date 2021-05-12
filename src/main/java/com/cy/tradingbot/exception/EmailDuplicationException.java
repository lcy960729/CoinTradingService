package com.cy.tradingbot.exception;

import com.cy.tradingbot.exception.enums.ErrorCode;

public class EmailDuplicationException extends BusinessException {
    public EmailDuplicationException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }
}
