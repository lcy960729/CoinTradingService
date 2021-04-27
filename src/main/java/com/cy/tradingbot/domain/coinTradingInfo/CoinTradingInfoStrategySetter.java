package com.cy.tradingbot.domain.coinTradingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinTradingInfoStrategySetter {
    private final CoinTradingInfoProgressingStrategy coinTradingInfoProgressingStrategy;

    private final CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy;

    private final CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy;

    public CoinTradingInfoStrategySetter(CoinTradingInfoProgressingStrategy coinTradingInfoProgressingStrategy, CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy, CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy) {
        this.coinTradingInfoProgressingStrategy = coinTradingInfoProgressingStrategy;
        this.coinTradingInfoWaitingStrategy = coinTradingInfoWaitingStrategy;
        this.coinTradingInfoPurchasedStrategy = coinTradingInfoPurchasedStrategy;
    }

    public void setProgressing(CoinTradingInfo coinTradingInfo) {
        coinTradingInfo.setCoinTradingInfoStrategy(coinTradingInfoProgressingStrategy);
    }

    public void setWaiting(CoinTradingInfo coinTradingInfo) {
        coinTradingInfo.setCoinTradingInfoStrategy(coinTradingInfoWaitingStrategy);
    }

    public void setPurchased(CoinTradingInfo coinTradingInfo) {
        coinTradingInfo.setCoinTradingInfoStrategy(coinTradingInfoPurchasedStrategy);
    }
}
