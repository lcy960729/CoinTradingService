package com.cy.tradingbot.domain.orderProccesor.service;


import com.cy.tradingbot.batch.OpenChapterService;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessorCompleteOfAllOrdersObserverOnStrategy implements OrderProcessorCompleteOfAllOrdersObserverStrategy {

    private final OpenChapterService openChapterService;

    public OrderProcessorCompleteOfAllOrdersObserverOnStrategy(OpenChapterService openChapterService) {
        this.openChapterService = openChapterService;
    }

    public void open() {
        openChapterService.open();
    }
}
