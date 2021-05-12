package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.SelectedCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class SelectedCoinMarketsStrategyService extends GetCoinMarketsStrategyService<RequestSelectedCoinMarketsStrategyDTO, ResponseSelectedCoinMarketsStrategyDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final SelectedCoinMarketsStrategyMapper selectedCoinMarketsStrategyMapper;

    protected SelectedCoinMarketsStrategyService(GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository, TradingBotRepository tradingBotRepository, SelectedCoinMarketsStrategyMapper selectedCoinMarketsStrategyMapper) {
        super(getCoinMarketsStrategyRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.selectedCoinMarketsStrategyMapper = selectedCoinMarketsStrategyMapper;
    }

    public ResponseSelectedCoinMarketsStrategyDTO create(long tradingBotId, RequestSelectedCoinMarketsStrategyDTO getCoinMarketsStrategyDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = new SelectedCoinMarketsStrategy();
        selectedCoinMarketsStrategy.setTradingBot(tradingBot);

        selectedCoinMarketsStrategy = getCoinMarketsStrategyRepository.save(selectedCoinMarketsStrategy);

        return selectedCoinMarketsStrategyMapper.toDTO(selectedCoinMarketsStrategy);
    }

    @Override
    public ResponseSelectedCoinMarketsStrategyDTO update(long getCoinMarketsStrategyId, RequestSelectedCoinMarketsStrategyDTO getCoinMarketsStrategyDTO) {
        return null;
    }

    @Override
    public ResponseSelectedCoinMarketsStrategyDTO get(long getCoinMarketsStrategyId) {
        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = (SelectedCoinMarketsStrategy) getCoinMarketsStrategyRepository.findById(getCoinMarketsStrategyId).orElseThrow(NotFoundEntityException::new);
        return selectedCoinMarketsStrategyMapper.toDTO(selectedCoinMarketsStrategy);
    }
}
