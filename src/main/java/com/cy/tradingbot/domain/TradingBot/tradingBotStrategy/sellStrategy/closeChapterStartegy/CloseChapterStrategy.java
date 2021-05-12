package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.closeChapterStartegy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("C")
@EntityListeners(CloseChapterStrategyEntityListener.class)
public class CloseChapterStrategy extends SellStrategy {

    @Transient
    private CloseChapterScheduler closeChapterScheduler;

    public void setCloseChapterScheduler(CloseChapterScheduler closeChapterScheduler) {
        this.closeChapterScheduler = closeChapterScheduler;
    }

    @Override
    public void initialize() {
        closeChapterScheduler.add(getTradingBot());
    }

    @Override
    public void run(CoinMarket coinMarket) {
    }

    @Override
    public void finalization() {
        closeChapterScheduler.remove(getTradingBot());
    }

    public CloseChapterScheduler getCloseChapterScheduler() {
        return closeChapterScheduler;
    }

}
