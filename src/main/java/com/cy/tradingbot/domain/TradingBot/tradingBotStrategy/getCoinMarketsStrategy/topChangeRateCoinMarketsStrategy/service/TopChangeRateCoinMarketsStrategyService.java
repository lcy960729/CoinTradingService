package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.service.GetCoinMarketsStrategyService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.request.RequestTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.TopChangeRateCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class TopChangeRateCoinMarketsStrategyService extends GetCoinMarketsStrategyService<TopChangeRateCoinMarketsStrategy, TopChangeRateCoinMarketsStrategyMapper, RequestTopChangeRateCoinMarketsStrategyDTO, ResponseTopChangeRateCoinMarketsStrategyDTO> {

    protected TopChangeRateCoinMarketsStrategyService(TradingBotService tradingBotService, GetCoinMarketsStrategyRepository repository, TopChangeRateCoinMarketsStrategyMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected TopChangeRateCoinMarketsStrategy createEntity(TradingBot tradingBot, RequestTopChangeRateCoinMarketsStrategyDTO requestTopChangeRateCoinMarketsStrategyDTO) {
        TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy = new TopChangeRateCoinMarketsStrategy();
        topChangeRateCoinMarketsStrategy.setTradingBot(tradingBot);

        updateEntity(topChangeRateCoinMarketsStrategy, requestTopChangeRateCoinMarketsStrategyDTO);

        return topChangeRateCoinMarketsStrategy;
    }

    @Override
    protected void updateEntity(TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy, RequestTopChangeRateCoinMarketsStrategyDTO requestTopChangeRateCoinMarketsStrategyDTO) {
        topChangeRateCoinMarketsStrategy.setMinOfPrice(requestTopChangeRateCoinMarketsStrategyDTO.getMinOfPrice());
    }
}
