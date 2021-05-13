package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.purchaseStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.volatilityBreakoutStrategy.VolatilityBreakoutStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.request.RequestVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.mapper.purchaseStrategy.VolatilityBreakoutStrategyMapper;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/volatilityBreakoutDailyStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class VolatilityBreakoutStrategyController extends PurchaseStrategyController<VolatilityBreakoutStrategy, VolatilityBreakoutStrategyMapper, RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO>{

    public VolatilityBreakoutStrategyController(TradingBotStrategyService<VolatilityBreakoutStrategy, PurchaseStrategyRepository, VolatilityBreakoutStrategyMapper, RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
