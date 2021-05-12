package com.cy.tradingbot.domain.tradingBot;

import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostLoad;

public class TradingBotEntityListener {
    @Lazy
    @Autowired
    private TradingBotRepository tradingBotRepository;

    @Autowired
    private TradingSignalObserver tradingSignalObserver;

    @PostLoad
    public void perPersist(TradingBot tradingBot){
        tradingBot.setTradingSignalObserver(tradingSignalObserver);
    }
}
