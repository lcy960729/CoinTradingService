package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.coin.coinExchange.CoinExchange;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostLoad;

public class GetCoinMarketsStrategyEntityListener {
    @Lazy
    @Autowired
    private GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository;

    @Autowired
    private CoinExchange coinExchange;

    @PostLoad
    public void prePersist(GetCoinMarketsStrategy getCoinMarketsStrategy){
        getCoinMarketsStrategy.setCoinExchange(coinExchange);
    }
}
