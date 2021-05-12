package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.SellStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellStrategyRepository extends JpaRepository<SellStrategy, Long> {
}
