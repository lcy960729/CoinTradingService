package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedAmount.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedAmount.FixedAmountCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedAmountCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedAmountCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.FixedAmountCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class FixedAmountCalculatorService extends InvestmentCalculatorService<FixedAmountCalculator, FixedAmountCalculatorMapper, RequestFixedAmountCalculatorDTO, ResponseFixedAmountCalculatorDTO> {

    protected FixedAmountCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, FixedAmountCalculatorMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected FixedAmountCalculator createEntity(TradingBot tradingBot, RequestFixedAmountCalculatorDTO requestFixedAmountCalculatorDTO) {
        FixedAmountCalculator fixedAmountCalculator = new FixedAmountCalculator();
        fixedAmountCalculator.setTradingBot(tradingBot);

        updateEntity(fixedAmountCalculator, requestFixedAmountCalculatorDTO);

        return fixedAmountCalculator;
    }

    @Override
    protected void updateEntity(FixedAmountCalculator fixedAmountCalculator, RequestFixedAmountCalculatorDTO requestFixedAmountCalculatorDTO) {
        fixedAmountCalculator.setFixedAmount(requestFixedAmountCalculatorDTO.getFixedAmount());

        setParent(fixedAmountCalculator, requestFixedAmountCalculatorDTO.getParentId());
    }
}
