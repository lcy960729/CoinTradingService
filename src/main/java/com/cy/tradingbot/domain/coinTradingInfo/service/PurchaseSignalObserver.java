package com.cy.tradingbot.domain.coinTradingInfo.service;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoStrategySetter;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSignalObserver {
    private final OrderSheetPublisher orderSheetPublisher;
    private final CoinTradingInfoStrategySetter coinTradingInfoStrategySetter;

    public PurchaseSignalObserver(OrderSheetPublisher orderSheetPublisher, CoinTradingInfoStrategySetter coinTradingInfoStrategySetter) {
        this.orderSheetPublisher = orderSheetPublisher;
        this.coinTradingInfoStrategySetter = coinTradingInfoStrategySetter;
    }

    public void notify(CoinTradingInfo coinTradingInfo) {
        if (coinTradingInfo.canPurchaseIt()) {
            coinTradingInfoStrategySetter.setProgressing(coinTradingInfo);
            orderSheetPublisher.publish(coinTradingInfo.createPurchaseOrderSheet());
        }
    }
}
