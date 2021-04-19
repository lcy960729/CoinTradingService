package com.cy.tradingbot.domain.orderProccesor.service;


import org.springframework.stereotype.Service;

@Service
public class OrderProcessorCompleteOfAllOrdersObserverStrategySetter {

    private final OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver;

    private final OrderProcessorCompleteOfAllOrdersObserverOffStrategy orderProcessorCompleteOfAllOrdersObserverOffStrategy;

    private final OrderProcessorCompleteOfAllOrdersObserverOnStrategy orderProcessorCompleteOfAllOrdersObserverOnStrategy;

    public OrderProcessorCompleteOfAllOrdersObserverStrategySetter(OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver, OrderProcessorCompleteOfAllOrdersObserverOffStrategy orderProcessorCompleteOfAllOrdersObserverOffStrategy, OrderProcessorCompleteOfAllOrdersObserverOnStrategy orderProcessorCompleteOfAllOrdersObserverOnStrategy) {
        this.orderProcessorCompleteOfAllOrdersObserver = orderProcessorCompleteOfAllOrdersObserver;
        this.orderProcessorCompleteOfAllOrdersObserverOffStrategy = orderProcessorCompleteOfAllOrdersObserverOffStrategy;
        this.orderProcessorCompleteOfAllOrdersObserverOnStrategy = orderProcessorCompleteOfAllOrdersObserverOnStrategy;
    }

    public void off() {
        orderProcessorCompleteOfAllOrdersObserver.setOrderProcessorCompleteOfAllOrdersObserverStrategy(orderProcessorCompleteOfAllOrdersObserverOffStrategy);
    }

    public void on() {
        orderProcessorCompleteOfAllOrdersObserver.setOrderProcessorCompleteOfAllOrdersObserverStrategy(orderProcessorCompleteOfAllOrdersObserverOnStrategy);
    }
}
