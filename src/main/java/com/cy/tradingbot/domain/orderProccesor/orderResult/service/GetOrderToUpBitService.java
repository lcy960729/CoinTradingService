package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrderToUpBitService implements GetOrderService {
    private final UpBitAPI upbitAPI;

    public GetOrderToUpBitService(UpBitAPI upbitAPI) {
        this.upbitAPI = upbitAPI;
    }

    public OrderResult getOrder(OrderResult orderResult) {
        return upbitAPI.getOrder(orderResult).orElseThrow(RuntimeException::new);
    }
}
