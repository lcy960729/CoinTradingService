package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.closeChapterStartegy;

import com.cy.tradingbot.repository.SellStrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostLoad;

public class CloseChapterStrategyEntityListener {
    @Lazy
    @Autowired
    private SellStrategyRepository sellStrategyRepository;

    @Autowired
    private CloseChapterScheduler closeChapterScheduler;

    @PostLoad
    public void prePersist(CloseChapterStrategy closeChapterStrategy){
        closeChapterStrategy.setCloseChapterScheduler(closeChapterScheduler);
    }
}
