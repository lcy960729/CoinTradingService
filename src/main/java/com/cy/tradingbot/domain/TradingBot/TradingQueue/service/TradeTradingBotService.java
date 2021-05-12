package com.cy.tradingbot.domain.tradingBot.TradingQueue.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.TradingQueue.TradingQueue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TradeTradingBotService {

    private final TradingQueue tradingQueue;

    public TradeTradingBotService(TradingQueue tradingQueue) {
        this.tradingQueue = tradingQueue;
    }

    @Async
    @Scheduled(fixedRate = 50)
    @Transactional
    public void trade() {
        TradingBot tradingBot = tradingQueue.get();

        if (tradingBot == null) return;

        tradingBot.trade();

        if (tradingQueue.isRunning(tradingBot))
            tradingQueue.add(tradingBot);
    }
}
