package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedAmount.FixedAmountCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestFixedAmountCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedAmountCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.FixedAmountCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/fixedAmountCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class FixedAmountCalculatorController extends InvestmentCalculatorController<FixedAmountCalculator, FixedAmountCalculatorMapper, RequestFixedAmountCalculatorDTO,ResponseFixedAmountCalculatorDTO> {

    public FixedAmountCalculatorController(TradingBotStrategyService<FixedAmountCalculator, InvestmentCalculatorRepository, FixedAmountCalculatorMapper, RequestFixedAmountCalculatorDTO, ResponseFixedAmountCalculatorDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
