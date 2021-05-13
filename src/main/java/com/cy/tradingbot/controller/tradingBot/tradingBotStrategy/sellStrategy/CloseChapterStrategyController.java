package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.sellStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.request.RequestCloseChapterStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
import com.cy.tradingbot.mapper.sellStrategy.CloseChapterStrategyMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/closeChapterStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class CloseChapterStrategyController extends SellStrategyController<CloseChapterStrategy, CloseChapterStrategyMapper, RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> {

    public CloseChapterStrategyController(TradingBotStrategyService<CloseChapterStrategy, SellStrategyRepository, CloseChapterStrategyMapper, RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
