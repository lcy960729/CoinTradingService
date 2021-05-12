package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedAmountCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedAmountCalculatorDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/fixedAmountCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class FixedAmountCalculatorController extends InvestmentCalculatorController<RequestFixedAmountCalculatorDTO, ResponseFixedAmountCalculatorDTO> {
    public FixedAmountCalculatorController(InvestmentCalculatorService<RequestFixedAmountCalculatorDTO, ResponseFixedAmountCalculatorDTO> investmentCalculatorService) {
        super(investmentCalculatorService);
    }
}
