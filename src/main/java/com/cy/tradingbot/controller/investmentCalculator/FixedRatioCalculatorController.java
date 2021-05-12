package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedRatioCalculatorDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/fixedRatioCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class FixedRatioCalculatorController extends InvestmentCalculatorController<RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> {
    public FixedRatioCalculatorController(InvestmentCalculatorService<RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> investmentCalculatorService) {
        super(investmentCalculatorService);
    }
}
