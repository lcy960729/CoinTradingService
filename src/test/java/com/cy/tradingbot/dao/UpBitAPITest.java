package com.cy.tradingbot.dao;

import com.cy.tradingbot.domain.market.Market;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;


class UpBitAPITest {

    private UpBitAPI upBitAPI = new UpBitAPI();

    @Test
    void getAllCoin() {
//        List<Market> marketList = upBitAPI.getAllCoins().orElseThrow(RuntimeException::new).stream().filter(market -> market.getMarket().contains("KRW-")).collect(Collectors.toList());
//        for (Market market : marketList) {
//            System.out.println(market);
//        }
    }
}