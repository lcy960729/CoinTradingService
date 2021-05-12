package com.cy.tradingbot.domain.orderProccesor;

import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class OrderProcessor {
    private final Queue<OrderSheet> requestQueue = new LinkedList<>();
    private final Queue<OrderResult> processQueue = new LinkedList<>();

    public void addOrderSheet(OrderSheet orderSheet) {
        requestQueue.offer(orderSheet);
    }

    public void addOrderResult(OrderResult orderResult) {
        processQueue.offer(orderResult);
    }

    public OrderSheet getOrderSheet() {
        return requestQueue.poll();
    }

    public OrderResult getOrderResult() {
        return processQueue.poll();
    }
}
