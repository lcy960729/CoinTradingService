package com.cy.tradingbot.controller.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service.SelectedCoinMarketsStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinMarketsStrategyDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/selectedCoinMarketsStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class SelectedCoinMarketsStrategyController extends GetCoinMarketsStrategyController<RequestSelectedCoinMarketsStrategyDTO, ResponseSelectedCoinMarketsStrategyDTO> {
    public SelectedCoinMarketsStrategyController(SelectedCoinMarketsStrategyService selectedCoinMarketsStrategyService) {
        super(selectedCoinMarketsStrategyService);
    }
}
