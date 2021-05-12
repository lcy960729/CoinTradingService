package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedRatio.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedRatio.FixedRatioCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedRatioCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.FixedRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class FixedRatioCalculatorService  extends InvestmentCalculatorService<FixedRatioCalculator, FixedRatioCalculatorMapper, RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> {

    protected FixedRatioCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, FixedRatioCalculatorMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected FixedRatioCalculator createEntity(TradingBot tradingBot, RequestFixedRatioCalculatorDTO requestFixedRatioCalculatorDTO) {
        FixedRatioCalculator fixedRatioCalculator = new FixedRatioCalculator();
        fixedRatioCalculator.setTradingBot(tradingBot);

        updateEntity(fixedRatioCalculator, requestFixedRatioCalculatorDTO);

        return fixedRatioCalculator;
    }

    @Override
    protected void updateEntity(FixedRatioCalculator fixedRatioCalculator, RequestFixedRatioCalculatorDTO requestFixedRatioCalculatorDTO) {
        fixedRatioCalculator.setFixedRatio(requestFixedRatioCalculatorDTO.getFixedRatio());

        setParent(fixedRatioCalculator, requestFixedRatioCalculatorDTO.getParentId());
    }
}
