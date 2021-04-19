package com.cy.tradingbot.domain.orderProccesor.orderSheet.service;

import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;

public interface CreateOrderService {
    OrderResult create(OrderSheet orderSheet);
}
