package com.cy.tradingbot.domain.coin.coinExchange;

import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.CoinMarket;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CoinExchange {
    private final Map<String, CoinMarket> coinMarketHashMap = new HashMap<>();

    public void updateCurrentPrice(String coinName, double currentPrice) {
        coinMarketHashMap.get(coinName).setCurrentPrice(currentPrice);
    }

    public void addCoinMarkets(List<Coin> coins) {
        for (Coin coin : coins) {
            coinMarketHashMap.put(coin.getMarketName(), new CoinMarket(coin));
        }
    }

    public Set<String> getCoins(){
        return coinMarketHashMap.keySet();
    }

    public List<CoinMarket> getCoinMarkets() {
        return new ArrayList<>(coinMarketHashMap.values());
    }

    public CoinMarket getCoinMarket(String coinName) {
        return coinMarketHashMap.get(coinName);
    }
}