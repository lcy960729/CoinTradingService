package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.orderProccesor.OrderProcessor;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessSubscriber {

    private final OrderProcessor orderProcessor;
    private final GetOrderService getOrderService;
    private final OrderProcessServiceLocator orderProcessServiceLocator;

    public OrderProcessSubscriber(OrderProcessor orderProcessor, GetOrderService getOrderService, OrderProcessServiceLocator orderProcessServiceLocator) {
        this.orderProcessor = orderProcessor;
        this.getOrderService = getOrderService;
        this.orderProcessServiceLocator = orderProcessServiceLocator;
    }

    @Async
    @Scheduled(fixedRate = 100)
    public void subscribe() {
        OrderResult orderResult = orderProcessor.getOrderResult();

        if (orderResult == null) return;

        process(orderResult);
    }

    private void process(OrderResult orderResult) {
        orderResult = getOrderService.getOrder(orderResult);

        OrderProcessService completedService = orderProcessServiceLocator.getService(orderResult);
        completedService.completed(orderResult);
    }
}
