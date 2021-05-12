package com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.volatilityBreakoutStrategy;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.service.GetTradingBotService;
import com.cy.tradingbot.domain.TradingBot.strategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.mapper.purchaseStrategy.VolatilityBreakoutStrategyMapper;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class VolatilityBreakoutDailyTradingBotStrategyService
        extends TradingBotStrategyService<VolatilityBreakoutStrategy, PurchaseStrategyRepository, VolatilityBreakoutStrategyMapper, RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> {

    protected VolatilityBreakoutDailyTradingBotStrategyService(GetTradingBotService getTradingBotService, PurchaseStrategyRepository repository, VolatilityBreakoutStrategyMapper mapper) {
        super(getTradingBotService, repository, mapper);
    }

    @Override
    protected VolatilityBreakoutStrategy createEntity(TradingBot tradingBot, RequestVolatilityBreakoutStrategyDTO requestVolatilityBreakoutStrategyDTO) {
        VolatilityBreakoutStrategy volatilityBreakoutStrategy = new VolatilityBreakoutStrategy();
        volatilityBreakoutStrategy.setTradingBot(tradingBot);
        volatilityBreakoutStrategy.setMaxOfCandles(requestVolatilityBreakoutStrategyDTO.getMaxOfCandles());

        return volatilityBreakoutStrategy;
    }

    @Override
    protected void updateEntity(VolatilityBreakoutStrategy volatilityBreakoutStrategy, RequestVolatilityBreakoutStrategyDTO requestVolatilityBreakoutStrategyDTO) {
        volatilityBreakoutStrategy.setMaxOfCandles(requestVolatilityBreakoutStrategyDTO.getMaxOfCandles());
    }
}
