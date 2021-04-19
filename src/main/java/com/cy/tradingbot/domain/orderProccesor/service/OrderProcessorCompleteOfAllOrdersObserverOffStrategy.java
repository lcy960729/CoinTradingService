package com.cy.tradingbot.domain.orderProccesor.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("OrderProcessorCompleteOfAllOrdersObserverOffStrategy")
public class OrderProcessorCompleteOfAllOrdersObserverOffStrategy implements OrderProcessorCompleteOfAllOrdersObserverStrategy {
    public void open() {
    }
}
