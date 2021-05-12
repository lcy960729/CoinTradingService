package com.cy.tradingbot.domain.orderProccesor.orderSheet.service;

import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import com.cy.tradingbot.domain.orderProccesor.OrderProcessor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderRequestSubscriber {
    private final OrderProcessor orderProcessor;
    private final CreateOrderService createOrderService;

    public OrderRequestSubscriber(OrderProcessor orderProcessor, CreateOrderService createOrderService) {
        this.orderProcessor = orderProcessor;
        this.createOrderService = createOrderService;
    }

    @Async
    @Scheduled(fixedRate = 100)
    public void subscribe() {
        OrderSheet orderSheet = orderProcessor.getOrderSheet();

        if (orderSheet == null) return;

        OrderResult orderResult = createOrderService.create(orderSheet);
        orderProcessor.addOrderResult(orderResult);
    }
}
