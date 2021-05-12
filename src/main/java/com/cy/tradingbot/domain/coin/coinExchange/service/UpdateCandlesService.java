package com.cy.tradingbot.domain.coin.coinExchange.service;

import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.coin.candle.Candle;
import com.cy.tradingbot.domain.coin.candle.service.GetCandlesService;
import com.cy.tradingbot.domain.coin.coinExchange.CoinExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateCandlesService {

    private final CoinExchange coinExchange;

    private final GetCandlesService getCandlesService;

    public UpdateCandlesService(GetCandlesService getCandlesService, CoinExchange coinExchange) {
        this.getCandlesService = getCandlesService;
        this.coinExchange = coinExchange;
    }

    @Scheduled(cron = "10 0 9 * * *", zone = "Asia/Seoul")
    public void updateCandles() {
        for (CoinMarket coinMarket : coinExchange.getCoinMarkets()) {
            List<Candle> candles = getCandlesService.getCandles(coinMarket.getMarketName(), 30);
            coinMarket.updateCandles(candles);
        }
    }
}
