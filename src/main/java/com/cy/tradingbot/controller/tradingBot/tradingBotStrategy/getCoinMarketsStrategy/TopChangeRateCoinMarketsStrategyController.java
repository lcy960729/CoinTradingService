package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.TopChangeRateCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/topChangeRateCoinMarketsStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class TopChangeRateCoinMarketsStrategyController extends GetCoinMarketsStrategyController<TopChangeRateCoinMarketsStrategy, TopChangeRateCoinMarketsStrategyMapper, RequestTopChangeRateCoinMarketsStrategyDTO, ResponseTopChangeRateCoinMarketsStrategyDTO> {
    public TopChangeRateCoinMarketsStrategyController(TradingBotStrategyService<TopChangeRateCoinMarketsStrategy, GetCoinMarketsStrategyRepository, TopChangeRateCoinMarketsStrategyMapper, RequestTopChangeRateCoinMarketsStrategyDTO, ResponseTopChangeRateCoinMarketsStrategyDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
