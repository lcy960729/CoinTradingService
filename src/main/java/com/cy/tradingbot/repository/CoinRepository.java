package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.CoinMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByCoinNameLike(String coinName);
}
