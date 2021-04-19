package com.cy.tradingbot.domain.coinTradingInfo;

import org.springframework.stereotype.Component;

@Component
public class CoinTradingInfoWaitingStrategy implements CoinTradingInfoStrategy {
    @Override
    public boolean canPurchaseIt(double currentPrice, double targetPrice) {
        return currentPrice > targetPrice;
    }

    @Override
    public boolean canStop() {
        return true;
    }
}

