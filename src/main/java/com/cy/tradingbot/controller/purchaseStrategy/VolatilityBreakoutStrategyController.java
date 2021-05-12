package com.cy.tradingbot.controller.purchaseStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.service.PurchaseStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/volatilityBreakoutDailyStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class VolatilityBreakoutStrategyController extends PurchaseStrategyController<RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> {
    public VolatilityBreakoutStrategyController(PurchaseStrategyService<RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> purchaseStrategyService) {
        super(purchaseStrategyService);
    }
}
