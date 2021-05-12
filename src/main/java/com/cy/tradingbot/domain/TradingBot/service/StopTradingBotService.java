package com.cy.tradingbot.domain.TradingBot.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.TradingQueue.TradingQueue;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class StopTradingBotService {
    private final TradingBotRepository tradingBotRepository;
    private final GetTradingBotService getTradingBotService;
    private final TradingQueue tradingQueue;

    public StopTradingBotService(TradingQueue tradingQueue, TradingBotRepository tradingBotRepository, GetTradingBotService getTradingBotService) {
        this.tradingQueue = tradingQueue;
        this.tradingBotRepository = tradingBotRepository;
        this.getTradingBotService = getTradingBotService;
    }

    public void stop(User user, long tradingBotId) {
        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);
        tradingQueue.stop(tradingBot);

        tradingBot.finalization();
        tradingBot = tradingBotRepository.save(tradingBot);
    }
}
