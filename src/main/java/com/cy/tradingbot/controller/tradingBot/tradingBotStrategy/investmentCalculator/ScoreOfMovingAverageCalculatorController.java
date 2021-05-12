package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.scoreOfMovingAverage.ScoreOfMovingAverageCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.ScoreOfMovingAverageCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/scoreOfMovingAverageCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class ScoreOfMovingAverageCalculatorController extends InvestmentCalculatorController<ScoreOfMovingAverageCalculator, ScoreOfMovingAverageCalculatorMapper, RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> {

    public ScoreOfMovingAverageCalculatorController(TradingBotStrategyService<ScoreOfMovingAverageCalculator, InvestmentCalculatorRepository, ScoreOfMovingAverageCalculatorMapper, RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
