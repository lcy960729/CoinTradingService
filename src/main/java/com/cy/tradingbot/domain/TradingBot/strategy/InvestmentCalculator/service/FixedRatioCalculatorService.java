package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.FixedRatioCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedRatioCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.investmentCalculator.FixedRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class FixedRatioCalculatorService extends InvestmentCalculatorService<RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> {
    private final TradingBotRepository tradingBotRepository;

    private final FixedRatioCalculatorMapper fixedRatioCalculatorMapper;
    public FixedRatioCalculatorService(TradingBotRepository tradingBotRepository, InvestmentCalculatorRepository investmentCalculatorRepository, FixedRatioCalculatorMapper fixedRatioCalculatorMapper) {
        super(investmentCalculatorRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.fixedRatioCalculatorMapper = fixedRatioCalculatorMapper;
    }

    public ResponseFixedRatioCalculatorDTO create(long tradingId, RequestFixedRatioCalculatorDTO fixedRatioCalculatorDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingId).orElseThrow(NotFoundEntityException::new);

        FixedRatioCalculator fixedRatioCalculator = new FixedRatioCalculator();
        fixedRatioCalculator.setFixedRatio(fixedRatioCalculatorDTO.getFixedRatio());
        fixedRatioCalculator.setTradingBot(tradingBot);

        setParent(fixedRatioCalculator, fixedRatioCalculatorDTO.getParentId());

        fixedRatioCalculator = investmentCalculatorRepository.save(fixedRatioCalculator);

        return fixedRatioCalculatorMapper.toDTO(fixedRatioCalculator);
    }

    @Override
    public ResponseFixedRatioCalculatorDTO update(long investmentCalculatorId, RequestFixedRatioCalculatorDTO fixedRatioCalculatorDTO) {
        FixedRatioCalculator fixedRatioCalculator = (FixedRatioCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new);
        fixedRatioCalculator.setFixedRatio(fixedRatioCalculatorDTO.getFixedRatio());

        setParent(fixedRatioCalculator, fixedRatioCalculatorDTO.getParentId());

        fixedRatioCalculator = investmentCalculatorRepository.save(fixedRatioCalculator);

        return fixedRatioCalculatorMapper.toDTO(fixedRatioCalculator);
    }

    @Override
    public ResponseFixedRatioCalculatorDTO get(long investmentCalculatorId) {
        FixedRatioCalculator fixedRatioCalculator = ((FixedRatioCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));
        return fixedRatioCalculatorMapper.toDTO(fixedRatioCalculator);
    }
}
