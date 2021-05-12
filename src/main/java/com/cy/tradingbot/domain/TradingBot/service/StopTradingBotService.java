package com.cy.tradingbot.domain.tradingBot.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.TradingQueue.TradingQueue;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class StopTradingBotService {
    private final TradingBotRepository tradingBotRepository;
    private TradingBotService tradingBotService;
    private final TradingQueue tradingQueue;

    public StopTradingBotService(TradingQueue tradingQueue, TradingBotRepository tradingBotRepository) {
        this.tradingQueue = tradingQueue;
        this.tradingBotRepository = tradingBotRepository;
    }

    public void stop(User user, long tradingBotId) {
        TradingBot tradingBot = tradingBotService.getEntity(user, tradingBotId);
        tradingQueue.stop(tradingBot);

        tradingBot.finalization();
        tradingBot = tradingBotRepository.save(tradingBot);
    }
}
