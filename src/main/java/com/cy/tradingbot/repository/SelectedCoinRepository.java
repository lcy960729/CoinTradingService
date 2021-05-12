package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectedCoinRepository extends JpaRepository<SelectedCoin, Long> {
    Optional<List<SelectedCoin>> findAllBySelectedCoinMarketsStrategyId(long selectedCoinMarketsStrategyId);
}
