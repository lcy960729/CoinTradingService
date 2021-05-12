package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.SelectedCoinMarketsStrategyMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/selectedCoinMarketsStrategies", produces = MediaTypes.HAL_JSON_VALUE)
public class SelectedCoinMarketsStrategyController extends GetCoinMarketsStrategyController<SelectedCoinMarketsStrategy, SelectedCoinMarketsStrategyMapper, RequestSelectedCoinMarketsStrategyDTO, ResponseSelectedCoinMarketsStrategyDTO> {

    public SelectedCoinMarketsStrategyController(TradingBotStrategyService<SelectedCoinMarketsStrategy, GetCoinMarketsStrategyRepository, SelectedCoinMarketsStrategyMapper, RequestSelectedCoinMarketsStrategyDTO, ResponseSelectedCoinMarketsStrategyDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
