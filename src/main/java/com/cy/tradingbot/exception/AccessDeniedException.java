package com.cy.tradingbot.exception;

import com.cy.tradingbot.exception.BusinessException;
import com.cy.tradingbot.exception.enums.ErrorCode;

public class AccessDeniedException extends BusinessException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
