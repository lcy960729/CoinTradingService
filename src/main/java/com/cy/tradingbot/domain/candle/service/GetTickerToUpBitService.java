package com.cy.tradingbot.domain.candle.service;

import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.candle.Candle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GetTickerToUpBitService implements GetTickerService {
    @Autowired
    private UpBitAPI upbitAPI;

    public List<Candle> getTicker(Set<String> coinNameList) {
        if (coinNameList.isEmpty()) return null;

        return upbitAPI.getTicker(
                coinNameList.stream()
                        .map(coinName -> "KRW-" + coinName)
                        .collect(Collectors.toSet())
        ).orElseThrow(RuntimeException::new);
    }
}
