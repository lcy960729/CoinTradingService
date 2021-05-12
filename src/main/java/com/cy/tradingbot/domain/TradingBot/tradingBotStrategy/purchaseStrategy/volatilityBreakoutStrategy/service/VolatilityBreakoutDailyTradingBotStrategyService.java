package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.volatilityBreakoutStrategy.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.service.PurchaseStrategyService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.volatilityBreakoutStrategy.VolatilityBreakoutStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.mapper.purchaseStrategy.VolatilityBreakoutStrategyMapper;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class VolatilityBreakoutDailyTradingBotStrategyService
        extends PurchaseStrategyService<VolatilityBreakoutStrategy, VolatilityBreakoutStrategyMapper, RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> {

    protected VolatilityBreakoutDailyTradingBotStrategyService(TradingBotService tradingBotService, PurchaseStrategyRepository repository, VolatilityBreakoutStrategyMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected VolatilityBreakoutStrategy createEntity(TradingBot tradingBot, RequestVolatilityBreakoutStrategyDTO requestVolatilityBreakoutStrategyDTO) {
        VolatilityBreakoutStrategy volatilityBreakoutStrategy = new VolatilityBreakoutStrategy();
        volatilityBreakoutStrategy.setTradingBot(tradingBot);

        updateEntity(volatilityBreakoutStrategy, requestVolatilityBreakoutStrategyDTO);

        return volatilityBreakoutStrategy;
    }

    @Override
    protected void updateEntity(VolatilityBreakoutStrategy volatilityBreakoutStrategy, RequestVolatilityBreakoutStrategyDTO requestVolatilityBreakoutStrategyDTO) {
        volatilityBreakoutStrategy.setMaxOfCandles(requestVolatilityBreakoutStrategyDTO.getMaxOfCandles());
    }
}
