package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseScoreOfMovingAverageCalculatorDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/scoreOfMovingAverageCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class ScoreOfMovingAverageCalculatorController extends InvestmentCalculatorController<RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> {
    public ScoreOfMovingAverageCalculatorController(InvestmentCalculatorService<RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> investmentCalculatorService) {
        super(investmentCalculatorService);
    }
}
