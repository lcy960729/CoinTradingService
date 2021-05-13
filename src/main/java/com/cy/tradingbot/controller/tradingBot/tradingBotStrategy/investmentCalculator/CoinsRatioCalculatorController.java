package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.coinRatio.CoinsRatioCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseCoinsRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestCoinsRatioCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.CoinsRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/coinsRatioCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class CoinsRatioCalculatorController extends InvestmentCalculatorController<CoinsRatioCalculator, CoinsRatioCalculatorMapper, RequestCoinsRatioCalculatorDTO, ResponseCoinsRatioCalculatorDTO> {

    public CoinsRatioCalculatorController(TradingBotStrategyService<CoinsRatioCalculator, InvestmentCalculatorRepository, CoinsRatioCalculatorMapper, RequestCoinsRatioCalculatorDTO, ResponseCoinsRatioCalculatorDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
