package com.cy.tradingbot.domain.orderProccesor;

import com.cy.tradingbot.domain.orderProccesor.OrderProcessor;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import org.springframework.stereotype.Service;

@Service
public class OrderSheetPublisher {
    private final OrderProcessor orderProcessor;

    public OrderSheetPublisher(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    public void publish(OrderSheet orderSheet) {
        orderProcessor.addOrderSheet(orderSheet);
    }
}
