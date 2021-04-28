package com.cy.tradingbot.domain.candle.service;


import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.candle.Candle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCandlesToUpBitService implements GetCandlesService {
    private final UpBitAPI upbitAPI;

    public GetCandlesToUpBitService(UpBitAPI upbitAPI) {
        this.upbitAPI = upbitAPI;
    }

    public List<Candle> getCandles(String coinName, int maxOfCandles) {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return upbitAPI.getDayCandle(coinName, maxOfCandles).orElseThrow(RuntimeException::new);
    }
}
