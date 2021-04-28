package com.cy.tradingbot.domain.market.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.market.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMarketsService {
    private final UpBitAPI upBitAPI;

    public GetAllMarketsService(UpBitAPI upBitAPI) {
        this.upBitAPI = upBitAPI;
    }

    public List<Market> getAllMarkets(){
        return upBitAPI.getAllCoins().orElseThrow(RuntimeException::new);
    }
}
