package com.cy.tradingbot.domain.coinTradingInfo;

import org.springframework.stereotype.Component;

@Component
public class CoinTradingInfoProgressingStrategy implements CoinTradingInfoStrategy {
    @Override
    public boolean canPurchaseIt(double currentPrice, double targetPrice) {
        return false;
    }

    @Override
    public boolean canStop() {
        return false;
    }
}
