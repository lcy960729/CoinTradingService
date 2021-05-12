package com.cy.tradingbot.domain.TradingBot;

import com.cy.tradingbot.repository.TradingBotRepository;
import org.hibernate.annotations.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

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
