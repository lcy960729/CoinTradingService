package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradingBotRepository extends JpaRepository<TradingBot, Long> {
    Optional<List<TradingBot>> findAllByUserId(long userId);
}
