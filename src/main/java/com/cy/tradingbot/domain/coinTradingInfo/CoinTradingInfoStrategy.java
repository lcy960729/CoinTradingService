package com.cy.tradingbot.domain.coinTradingInfo;

public interface CoinTradingInfoStrategy {
    boolean canPurchaseIt(double currentPrice, double targetPrice);

    boolean canStop();
}
