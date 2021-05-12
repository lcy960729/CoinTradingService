package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseCoinsRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestCoinsRatioCalculatorDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/coinsRatioCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class CoinsRatioCalculatorController extends InvestmentCalculatorController<RequestCoinsRatioCalculatorDTO, ResponseCoinsRatioCalculatorDTO> {
    public CoinsRatioCalculatorController(InvestmentCalculatorService<RequestCoinsRatioCalculatorDTO, ResponseCoinsRatioCalculatorDTO> investmentCalculatorService) {
        super(investmentCalculatorService);
    }
}
