package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.ScoreOfMovingAverageCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.investmentCalculator.ScoreOfMovingAverageCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreOfMovingAverageCalculatorService extends InvestmentCalculatorService<RequestScoreOfMovingAverageCalculatorDTO, ResponseScoreOfMovingAverageCalculatorDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final ScoreOfMovingAverageCalculatorMapper scoreOfMovingAverageCalculatorMapper;

    public ScoreOfMovingAverageCalculatorService(TradingBotRepository tradingBotRepository, InvestmentCalculatorRepository investmentCalculatorRepository, ScoreOfMovingAverageCalculatorMapper scoreOfMovingAverageCalculatorMapper) {
        super(investmentCalculatorRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.scoreOfMovingAverageCalculatorMapper = scoreOfMovingAverageCalculatorMapper;
    }

    public ResponseScoreOfMovingAverageCalculatorDTO create(long tradingId, RequestScoreOfMovingAverageCalculatorDTO scoreOfMovingAverageCalculatorDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingId).orElseThrow(NotFoundEntityException::new);

        ScoreOfMovingAverageCalculator scoreOfMovingAverageCalculator = new ScoreOfMovingAverageCalculator();
        scoreOfMovingAverageCalculator.setNumOfMovingAverageWindow(scoreOfMovingAverageCalculatorDTO.getNumOfMovingAverageWindow());
        scoreOfMovingAverageCalculator.setTradingBot(tradingBot);

        setParent(scoreOfMovingAverageCalculator, scoreOfMovingAverageCalculatorDTO.getParentId());

        scoreOfMovingAverageCalculator = investmentCalculatorRepository.save(scoreOfMovingAverageCalculator);

        return scoreOfMovingAverageCalculatorMapper.toDTO(scoreOfMovingAverageCalculator);
    }

    @Override
    public ResponseScoreOfMovingAverageCalculatorDTO update(long investmentCalculatorId, RequestScoreOfMovingAverageCalculatorDTO scoreOfMovingAverageCalculatorDTO) {
        ScoreOfMovingAverageCalculator scoreOfMovingAverageCalculator = (ScoreOfMovingAverageCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new);
        scoreOfMovingAverageCalculator.setNumOfMovingAverageWindow(scoreOfMovingAverageCalculatorDTO.getNumOfMovingAverageWindow());

        setParent(scoreOfMovingAverageCalculator, scoreOfMovingAverageCalculatorDTO.getParentId());

        scoreOfMovingAverageCalculator = investmentCalculatorRepository.save(scoreOfMovingAverageCalculator);

        return scoreOfMovingAverageCalculatorMapper.toDTO(scoreOfMovingAverageCalculator);
    }

    @Override
    public ResponseScoreOfMovingAverageCalculatorDTO get(long investmentCalculatorId) {
        ScoreOfMovingAverageCalculator scoreOfMovingAverageCalculator = ((ScoreOfMovingAverageCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));
        return scoreOfMovingAverageCalculatorMapper.toDTO(scoreOfMovingAverageCalculator);
    }
}
