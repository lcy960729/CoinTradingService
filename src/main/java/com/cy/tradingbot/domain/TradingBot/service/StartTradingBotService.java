package com.cy.tradingbot.domain.tradingBot.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.TradingQueue.TradingQueue;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StartTradingBotService {

    private final TradingBotRepository tradingBotRepository;
    private final TradingBotService tradingBotService;
    private final TradingQueue tradingQueue;
    private final GetWalletService getWalletService;

    public StartTradingBotService(TradingBotRepository tradingBotRepository, TradingQueue tradingQueue, GetWalletService getWalletService, TradingBotService tradingBotService) {
        this.tradingBotRepository = tradingBotRepository;
        this.tradingQueue = tradingQueue;
        this.getWalletService = getWalletService;
        this.tradingBotService = tradingBotService;
    }

    public void start(User user, long tradingBotId) {
        TradingBot tradingBot = tradingBotService.getEntity(user, tradingBotId);

        Map<String, Wallet> wallets = getWalletService.getWalletHashTable(tradingBot.getUser().getCredential());

        tradingBot.initialization(wallets.get("KRW").getBalance());
        tradingBot = tradingBotRepository.save(tradingBot);

        tradingQueue.start(tradingBot);
    }
}
