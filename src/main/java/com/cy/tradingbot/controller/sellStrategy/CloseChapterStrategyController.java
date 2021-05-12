package com.cy.tradingbot.controller.sellStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service.SellStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestCloseChapterStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/closeChapterStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class CloseChapterStrategyController extends SellStrategyController<RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> {
    public CloseChapterStrategyController(SellStrategyService<RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> sellStrategyService) {
        super(sellStrategyService);
    }
}
