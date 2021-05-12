package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseYesterdayVolatilityCalculatorDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/yesterdayVolatilityCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class YesterdayVolatilityCalculatorController extends InvestmentCalculatorController<RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> {
    public YesterdayVolatilityCalculatorController(InvestmentCalculatorService<RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> investmentCalculatorService) {
        super(investmentCalculatorService);
    }
}
