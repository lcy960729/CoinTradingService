package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.orderProccesor.OrderProcessor;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReOrderProcessServiceStrategy implements OrderProcessService {
    @Autowired
    private  OrderProcessor orderProcessor;
    @Autowired
    private DeleteOrderService deleteOrderService;

    @Override
    public void completed(OrderResult orderResult) {
        deleteOrderService.deleteOrder(orderResult);
        orderProcessor.addOrderSheet(orderResult.getOrderSheet());
    }
}
