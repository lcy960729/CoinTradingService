package com.cy.tradingbot.domain.coin.candle.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.coin.candle.Candle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GetTickerToUpBitService implements GetTickerService {
    private final UpBitAPI upbitAPI;

    public GetTickerToUpBitService(UpBitAPI upbitAPI) {
        this.upbitAPI = upbitAPI;
    }

    public List<Candle> getTicker(Set<String> coinMarkets) {
        if (coinMarkets.isEmpty()) return null;

        return upbitAPI.getTicker(coinMarkets).orElseThrow(RuntimeException::new);
    }
}
