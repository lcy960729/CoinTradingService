package com.cy.tradingbot.domain.coin.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.CoinMarket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMarketsService {
    private final UpBitAPI upBitAPI;

    public GetAllMarketsService(UpBitAPI upBitAPI) {
        this.upBitAPI = upBitAPI;
    }

    public List<Coin> getAllMarkets(){
        return upBitAPI.getAllCoins().orElseThrow(RuntimeException::new);
    }
}
