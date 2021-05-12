package com.cy.tradingbot.domain.coin.candle.service;

import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.coin.candle.Candle;

import java.util.List;
import java.util.Set;

public interface GetTickerService {
    List<Candle> getTicker(Set<String> coinMarketName);
}
