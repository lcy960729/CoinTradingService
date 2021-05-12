package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service;

import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;

public abstract class GetCoinMarketsStrategyService<K extends RequestGetCoinMarketsStrategyDTO, V extends ResponseGetCoinMarketsStrategyDTO> {
    protected final GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository;

    protected GetCoinMarketsStrategyService(GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository) {
        this.getCoinMarketsStrategyRepository = getCoinMarketsStrategyRepository;
    }

    public abstract V create(long tradingBotId, K getCoinMarketsStrategyDTO);

    public abstract V update(long getCoinMarketsStrategyId, K getCoinMarketsStrategyDTO);

    public void delete(long getCoinMarketsStrategyId) {
        getCoinMarketsStrategyRepository.deleteById(getCoinMarketsStrategyId);
    }

    public abstract V get(long getCoinMarketsStrategyId);
}
