package com.cy.tradingbot.controller.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service.TopChangeRateCoinMarketsStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseTopChangeRateCoinMarketsStrategyDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/topChangeRateCoinMarketsStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class TopChangeRateCoinMarketsStrategyController extends GetCoinMarketsStrategyController<RequestTopChangeRateCoinMarketsStrategyDTO, ResponseTopChangeRateCoinMarketsStrategyDTO> {
    public TopChangeRateCoinMarketsStrategyController(TopChangeRateCoinMarketsStrategyService topChangeRateCoinMarketsStrategyService) {
        super(topChangeRateCoinMarketsStrategyService);
    }
}
