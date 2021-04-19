package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;

public interface OrderProcessService {
    void completed(OrderResult orderResult);
}
