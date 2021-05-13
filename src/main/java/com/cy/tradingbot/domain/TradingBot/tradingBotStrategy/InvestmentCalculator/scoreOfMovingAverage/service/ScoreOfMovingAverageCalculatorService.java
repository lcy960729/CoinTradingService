package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.scoreOfMovingAverage.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.scoreOfMovingAverage.ScoreOfMovingAverageCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.ScoreOfMovingAverageCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreOfMovingAverageCalculatorService extends InvestmentCalculatorService<ScoreOfMovingAverageCalculator, ScoreOfMovingAverageCalculatorMapper, RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> {

    protected ScoreOfMovingAverageCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, ScoreOfMovingAverageCalculatorMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected ScoreOfMovingAverageCalculator createEntity(TradingBot tradingBot, RequestScoreOfMovingAverageCalculatorDTO requestScoreOfMovingAverageCalculatorDTO) {
        ScoreOfMovingAverageCalculator scoreOfMovingAverageCalculator = new ScoreOfMovingAverageCalculator();
        scoreOfMovingAverageCalculator.setTradingBot(tradingBot);

        updateEntity(scoreOfMovingAverageCalculator, requestScoreOfMovingAverageCalculatorDTO);

        return scoreOfMovingAverageCalculator;
    }

    @Override
    protected void updateEntity(ScoreOfMovingAverageCalculator scoreOfMovingAverageCalculator, RequestScoreOfMovingAverageCalculatorDTO requestScoreOfMovingAverageCalculatorDTO) {
        scoreOfMovingAverageCalculator.setNumOfMovingAverageWindow(requestScoreOfMovingAverageCalculatorDTO.getNumOfMovingAverageWindow());

        setParent(scoreOfMovingAverageCalculator, requestScoreOfMovingAverageCalculatorDTO.getParentId());
    }
}
