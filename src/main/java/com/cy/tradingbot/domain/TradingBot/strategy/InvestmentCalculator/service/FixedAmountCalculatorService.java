package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.FixedAmountCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedAmountCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedAmountCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.investmentCalculator.FixedAmountCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class FixedAmountCalculatorService extends InvestmentCalculatorService<RequestFixedAmountCalculatorDTO, ResponseFixedAmountCalculatorDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final FixedAmountCalculatorMapper fixedAmountCalculatorMapper;

    public FixedAmountCalculatorService(TradingBotRepository tradingBotRepository, InvestmentCalculatorRepository investmentCalculatorRepository, FixedAmountCalculatorMapper fixedAmountCalculatorMapper) {
        super(investmentCalculatorRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.fixedAmountCalculatorMapper = fixedAmountCalculatorMapper;
    }

    @Override
    public ResponseFixedAmountCalculatorDTO update(long investmentCalculatorId, RequestFixedAmountCalculatorDTO fixedAmountCalculatorDTO) {
        FixedAmountCalculator fixedAmountCalculator = (FixedAmountCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new);
        fixedAmountCalculator.setFixedAmount(fixedAmountCalculatorDTO.getFixedAmount());

        setParent(fixedAmountCalculator, fixedAmountCalculatorDTO.getParentId());

        fixedAmountCalculator = investmentCalculatorRepository.save(fixedAmountCalculator);

        return fixedAmountCalculatorMapper.toDTO(fixedAmountCalculator);
    }

    public ResponseFixedAmountCalculatorDTO create(long tradingBotId, RequestFixedAmountCalculatorDTO fixedAmountCalculatorDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        FixedAmountCalculator fixedAmountCalculator = new FixedAmountCalculator();
        fixedAmountCalculator.setFixedAmount(fixedAmountCalculatorDTO.getFixedAmount());
        fixedAmountCalculator.setTradingBot(tradingBot);

        setParent(fixedAmountCalculator, fixedAmountCalculatorDTO.getParentId());

        fixedAmountCalculator = investmentCalculatorRepository.save(fixedAmountCalculator);

        return fixedAmountCalculatorMapper.toDTO(fixedAmountCalculator);
    }

    @Override
    public ResponseFixedAmountCalculatorDTO get(long investmentCalculatorId) {
        FixedAmountCalculator fixedAmountCalculator = ((FixedAmountCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));
        return fixedAmountCalculatorMapper.toDTO(fixedAmountCalculator);
    }
}
