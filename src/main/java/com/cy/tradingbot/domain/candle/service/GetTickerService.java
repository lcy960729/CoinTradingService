package com.cy.tradingbot.domain.candle.service;

import com.cy.tradingbot.domain.candle.Candle;

import java.util.List;
import java.util.Set;

public interface GetTickerService {
    List<Candle> getTicker(Set<String> coinName);
}
