package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedRatio.FixedRatioCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestFixedRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseFixedRatioCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.FixedRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/fixedRatioCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class FixedRatioCalculatorController extends InvestmentCalculatorController<FixedRatioCalculator, FixedRatioCalculatorMapper, RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> {
    public FixedRatioCalculatorController(TradingBotStrategyService<FixedRatioCalculator, InvestmentCalculatorRepository, FixedRatioCalculatorMapper, RequestFixedRatioCalculatorDTO, ResponseFixedRatioCalculatorDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
