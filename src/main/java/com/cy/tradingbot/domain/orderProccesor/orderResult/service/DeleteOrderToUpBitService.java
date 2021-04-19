package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderToUpBitService implements DeleteOrderService{
    @Autowired
    private UpBitAPI upbitAPI;

    public void deleteOrder(OrderResult orderResult){
        upbitAPI.deleteOrder(orderResult);
    }
}
