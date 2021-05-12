package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.InvestmentCalculator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentCalculatorRepository extends JpaRepository<InvestmentCalculator, Long> {
}
