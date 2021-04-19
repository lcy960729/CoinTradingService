package com.cy.tradingbot.domain.coinTradingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinTradingInfoStrategySetter {
    @Autowired
    private CoinTradingInfoProgressingStrategy coinTradingInfoProgressingStrategy;

    @Autowired
    private CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy;

    @Autowired
    private CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy;

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
