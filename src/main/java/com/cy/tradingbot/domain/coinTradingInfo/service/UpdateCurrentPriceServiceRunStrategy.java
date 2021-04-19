package com.cy.tradingbot.domain.coinTradingInfo.service;

import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.candle.service.GetTickerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Qualifier("UpdateCurrentPriceServiceRunStrategy")
public class UpdateCurrentPriceServiceRunStrategy implements UpdateCurrentPriceServiceStrategy {
    private final GetTickerService getTickerService;
    private final TradingInfoRepository tradingInfoRepository;
    private final PurchaseSignalObserver purchaseSignalObserver;

    public UpdateCurrentPriceServiceRunStrategy(GetTickerService getTickerService, TradingInfoRepository tradingInfoRepository, PurchaseSignalObserver purchaseSignalObserver) {
        this.getTickerService = getTickerService;
        this.tradingInfoRepository = tradingInfoRepository;
        this.purchaseSignalObserver = purchaseSignalObserver;
    }

    public void updateCurrentPrice() {
        List<Candle> tickers = getTickerService.getTicker(tradingInfoRepository.getCoinSet());

        if (tickers == null) return;

        tickers.forEach(ticker -> {
            final String coinName = ticker.getCoinName().substring(4);
            final double currentPrice = ticker.getTradePrice();

            Set<CoinTradingInfo> coinTradingInfoList = tradingInfoRepository.getTradingInfosOfCoin(coinName);

            coinTradingInfoList.forEach(coinTradingInfo -> {
                coinTradingInfo.updateCurrentPrice(currentPrice);

                purchaseSignalObserver.notify(coinTradingInfo);
            });
        });
    }

}
