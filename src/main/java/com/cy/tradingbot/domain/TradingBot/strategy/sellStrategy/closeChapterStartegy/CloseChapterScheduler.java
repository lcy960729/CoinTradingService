package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloseChapterScheduler {
    private final List<TradingBot> tradingBots = new ArrayList<>();

    @Scheduled(cron = "10 0 9 * * *")
    private void closeChapter() {
        tradingBots.forEach(TradingBot::sellPurchasedAllCoins);
    }

    public void add(TradingBot tradingBot) {
        tradingBots.add(tradingBot);
    }

    public void remove(TradingBot tradingBot) {
        tradingBots.remove(tradingBot);
    }
}
