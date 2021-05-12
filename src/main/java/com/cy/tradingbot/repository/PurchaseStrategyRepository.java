package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.PurchaseStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseStrategyRepository extends JpaRepository<PurchaseStrategy, Long> {
}
