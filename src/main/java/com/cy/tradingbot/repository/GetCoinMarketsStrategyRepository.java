package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetCoinMarketsStrategyRepository extends JpaRepository<GetCoinMarketsStrategy, Long> {
}
