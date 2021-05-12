package com.cy.tradingbot.exception;

import com.cy.tradingbot.exception.enums.ErrorCode;

public class NotFoundEntityException extends BusinessException{
    public NotFoundEntityException() {
        super(ErrorCode.NOT_FOUND_ENTITY);
    }
}
