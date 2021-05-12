package com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.service;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestPurchaseStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;

public abstract class PurchaseStrategyService<K extends RequestPurchaseStrategyDTO, V extends ResponsePurchaseStrategyDTO>{

    protected PurchaseStrategyRepository purchaseStrategyRepository;

    public PurchaseStrategyService(PurchaseStrategyRepository purchaseStrategyRepository) {
        this.purchaseStrategyRepository = purchaseStrategyRepository;
    }

    public abstract V create(User user, long tradingBotId, K requestPurchaseStrategyDTO);

    public abstract V update(User user, long tradingBotId, long purchaseStrategyId, K requestPurchaseStrategyDTO);

    public void delete(User user, long tradingBotId, long purchaseStrategyId) {
        purchaseStrategyRepository.deleteById(purchaseStrategyId);
    }

    public abstract V get(User user, long tradingBotId, long purchaseStrategyId);
}
