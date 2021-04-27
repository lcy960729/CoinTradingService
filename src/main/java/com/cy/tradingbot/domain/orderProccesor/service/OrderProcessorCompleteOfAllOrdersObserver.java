package com.cy.tradingbot.domain.orderProccesor.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessorCompleteOfAllOrdersObserver {

    private OrderProcessorCompleteOfAllOrdersObserverStrategy orderProcessorCompleteOfAllOrdersObserverStrategy;

    public OrderProcessorCompleteOfAllOrdersObserver(@Qualifier("OrderProcessorCompleteOfAllOrdersObserverOffStrategy") OrderProcessorCompleteOfAllOrdersObserverStrategy orderProcessorCompleteOfAllOrdersObserverStrategy) {
        this.orderProcessorCompleteOfAllOrdersObserverStrategy = orderProcessorCompleteOfAllOrdersObserverStrategy;
    }

    public void update() {
        orderProcessorCompleteOfAllOrdersObserverStrategy.open();
    }

    public void setOrderProcessorCompleteOfAllOrdersObserverStrategy(OrderProcessorCompleteOfAllOrdersObserverStrategy orderProcessorCompleteOfAllOrdersObserverStrategy) {
        this.orderProcessorCompleteOfAllOrdersObserverStrategy = orderProcessorCompleteOfAllOrdersObserverStrategy;
    }
}
