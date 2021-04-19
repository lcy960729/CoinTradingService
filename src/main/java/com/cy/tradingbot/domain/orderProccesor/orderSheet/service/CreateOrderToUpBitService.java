package com.cy.tradingbot.domain.orderProccesor.orderSheet.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderToUpBitService implements CreateOrderService {
    @Autowired
    private UpBitAPI upBitAPI;

    @Override
    public OrderResult create(OrderSheet orderSheet) {
        return upBitAPI.order(orderSheet).orElseThrow(RuntimeException::new);
    }
}
