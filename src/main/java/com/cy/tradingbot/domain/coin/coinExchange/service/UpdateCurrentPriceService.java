package com.cy.tradingbot.domain.coin.coinExchange.service;

import com.cy.tradingbot.domain.coin.coinExchange.CoinExchange;
import com.cy.tradingbot.domain.coin.candle.Candle;
import com.cy.tradingbot.domain.coin.candle.service.GetTickerService;
import com.cy.tradingbot.domain.coin.CoinMarket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UpdateCurrentPriceService {
    private final GetTickerService getTickerService;
    private final CoinExchange coinExchange;

    public UpdateCurrentPriceService(GetTickerService getTickerService, CoinExchange coinExchange) {
        this.getTickerService = getTickerService;
        this.coinExchange = coinExchange;
    }

    @Async
    @Scheduled(fixedDelay = 500)
    public void updateCurrentPrice() {
        List<Candle> tickers = getTickerService.getTicker(coinExchange.getCoins());

        if (tickers == null) return;

        for (Candle ticker : tickers) {
            coinExchange.updateCurrentPrice(ticker.getName(), ticker.getTradePrice());
        }
    }
}
