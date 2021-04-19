package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessServiceLocator {
    private final SellOrderProcessServiceStrategy sellOrderCompletedServiceStrategy;
    private final BuyOrderProcessServiceStrategy buyOrderCompletedServiceStrategy;
    private final ReOrderProcessServiceStrategy reOrderProcessServiceStrategy;
    private final OrderWaitingProcessServiceStrategy orderWaitingProcessServiceStrategy;

    public OrderProcessServiceLocator(SellOrderProcessServiceStrategy sellOrderCompletedServiceStrategy, BuyOrderProcessServiceStrategy buyOrderCompletedServiceStrategy, OrderWaitingProcessServiceStrategy orderWaitingProcessServiceStrategy, ReOrderProcessServiceStrategy reOrderProcessServiceStrategy) {
        this.sellOrderCompletedServiceStrategy = sellOrderCompletedServiceStrategy;
        this.buyOrderCompletedServiceStrategy = buyOrderCompletedServiceStrategy;
        this.orderWaitingProcessServiceStrategy = orderWaitingProcessServiceStrategy;
        this.reOrderProcessServiceStrategy = reOrderProcessServiceStrategy;
    }

    public OrderProcessService getService(OrderResult orderResult) {
        if (orderResult.isDone()) {
            return orderResult.isBuying() ? buyOrderCompletedServiceStrategy : sellOrderCompletedServiceStrategy;
        } else {
            return orderResult.is10secondsAfterOrder() ? reOrderProcessServiceStrategy : orderWaitingProcessServiceStrategy;
        }
    }
}
