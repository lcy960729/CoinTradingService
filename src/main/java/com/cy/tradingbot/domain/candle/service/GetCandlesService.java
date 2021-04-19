package com.cy.tradingbot.domain.candle.service;


import com.cy.tradingbot.domain.candle.Candle;

import java.util.List;

public interface GetCandlesService {
    List<Candle> getCandles(String coinName, int maxOfCandles);
}
