package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.TopChangeRateCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class TopChangeRateCoinMarketsStrategyService extends GetCoinMarketsStrategyService<RequestTopChangeRateCoinMarketsStrategyDTO, ResponseTopChangeRateCoinMarketsStrategyDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final TopChangeRateCoinMarketsStrategyMapper topChangeRateCoinMarketsStrategyMapper;

    protected TopChangeRateCoinMarketsStrategyService(GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository, TradingBotRepository tradingBotRepository, TopChangeRateCoinMarketsStrategyMapper topChangeRateCoinMarketsStrategyMapper) {
        super(getCoinMarketsStrategyRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.topChangeRateCoinMarketsStrategyMapper = topChangeRateCoinMarketsStrategyMapper;
    }

    @Override
    public ResponseTopChangeRateCoinMarketsStrategyDTO create(long tradingBotId, RequestTopChangeRateCoinMarketsStrategyDTO getCoinMarketsStrategyDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy = new TopChangeRateCoinMarketsStrategy();
        topChangeRateCoinMarketsStrategy.setTradingBot(tradingBot);

        topChangeRateCoinMarketsStrategy.setMinOfPrice(getCoinMarketsStrategyDTO.getMinOfPrice());

        topChangeRateCoinMarketsStrategy = getCoinMarketsStrategyRepository.save(topChangeRateCoinMarketsStrategy);

        return topChangeRateCoinMarketsStrategyMapper.toDTO(topChangeRateCoinMarketsStrategy);
    }

    @Override
    public ResponseTopChangeRateCoinMarketsStrategyDTO update(long getTopChangeRateCoinMarketsStrategyId,
                                                              RequestTopChangeRateCoinMarketsStrategyDTO getCoinMarketsStrategyDTO) {

        TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy = (TopChangeRateCoinMarketsStrategy) getCoinMarketsStrategyRepository.findById(getTopChangeRateCoinMarketsStrategyId).orElseThrow(NotFoundEntityException::new);
        topChangeRateCoinMarketsStrategy.setMinOfPrice(getCoinMarketsStrategyDTO.getMinOfPrice());

        topChangeRateCoinMarketsStrategy = getCoinMarketsStrategyRepository.save(topChangeRateCoinMarketsStrategy);

        return topChangeRateCoinMarketsStrategyMapper.toDTO(topChangeRateCoinMarketsStrategy);
    }

    @Override
    public ResponseTopChangeRateCoinMarketsStrategyDTO get(long getCoinMarketsStrategyId) {
        TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy = (TopChangeRateCoinMarketsStrategy) getCoinMarketsStrategyRepository.findById(getCoinMarketsStrategyId).orElseThrow(NotFoundEntityException::new);
        return topChangeRateCoinMarketsStrategyMapper.toDTO(topChangeRateCoinMarketsStrategy);
    }
}
