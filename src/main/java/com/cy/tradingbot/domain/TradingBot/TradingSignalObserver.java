package com.cy.tradingbot.domain.tradingBot;

import com.cy.tradingbot.domain.orderProccesor.OrderSheetPublisher;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import org.springframework.stereotype.Component;

@Component
public class TradingSignalObserver {
    private final OrderSheetPublisher orderSheetPublisher;

    public TradingSignalObserver(OrderSheetPublisher orderSheetPublisher) {
        this.orderSheetPublisher = orderSheetPublisher;
    }

    public void update(OrderSheet orderSheet) {
        orderSheetPublisher.publish(orderSheet);
    }
}
