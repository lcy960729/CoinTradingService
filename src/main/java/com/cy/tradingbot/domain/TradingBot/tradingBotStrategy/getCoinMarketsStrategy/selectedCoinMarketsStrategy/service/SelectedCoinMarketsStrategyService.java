package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.service.GetCoinMarketsStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.SelectedCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class SelectedCoinMarketsStrategyService extends GetCoinMarketsStrategyService<SelectedCoinMarketsStrategy, SelectedCoinMarketsStrategyMapper, RequestSelectedCoinMarketsStrategyDTO, ResponseSelectedCoinMarketsStrategyDTO> {

    protected SelectedCoinMarketsStrategyService(TradingBotService tradingBotService, GetCoinMarketsStrategyRepository repository, SelectedCoinMarketsStrategyMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected SelectedCoinMarketsStrategy createEntity(TradingBot tradingBot, RequestSelectedCoinMarketsStrategyDTO requestSelectedCoinMarketsStrategyDTO) {
        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = new SelectedCoinMarketsStrategy();
        selectedCoinMarketsStrategy.setTradingBot(tradingBot);

        updateEntity(selectedCoinMarketsStrategy, requestSelectedCoinMarketsStrategyDTO);

        return selectedCoinMarketsStrategy;
    }

    @Override
    protected void updateEntity(SelectedCoinMarketsStrategy selectedCoinMarketsStrategy, RequestSelectedCoinMarketsStrategyDTO requestSelectedCoinMarketsStrategyDTO) {
    }
}
