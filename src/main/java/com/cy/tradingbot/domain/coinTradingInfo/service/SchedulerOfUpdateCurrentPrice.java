package com.cy.tradingbot.domain.coinTradingInfo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerOfUpdateCurrentPrice {
    private UpdateCurrentPriceServiceStrategy updateCurrentPriceServiceStrategy;

    public SchedulerOfUpdateCurrentPrice(@Qualifier("UpdateCurrentPriceServiceRunStrategy") UpdateCurrentPriceServiceStrategy updateCurrentPriceServiceStrategy) {
        this.updateCurrentPriceServiceStrategy = updateCurrentPriceServiceStrategy;
    }

    @Scheduled(fixedDelay = 1000)
    public void updateCurrentPrice() {
        updateCurrentPriceServiceStrategy.updateCurrentPrice();
    }

    public void setUpdateCurrentPriceService(UpdateCurrentPriceServiceStrategy updateCurrentPriceServiceStrategy) {
        this.updateCurrentPriceServiceStrategy = updateCurrentPriceServiceStrategy;
    }
}
