package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.yesterdayVolatility.YesterdayVolatilityCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.YesterdayVolatilityCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/yesterdayVolatilityCalculators", produces = MediaTypes.HAL_JSON_VALUE)
public class YesterdayVolatilityCalculatorController extends InvestmentCalculatorController<YesterdayVolatilityCalculator, YesterdayVolatilityCalculatorMapper, RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> {

    public YesterdayVolatilityCalculatorController(TradingBotStrategyService<YesterdayVolatilityCalculator, InvestmentCalculatorRepository, YesterdayVolatilityCalculatorMapper, RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
