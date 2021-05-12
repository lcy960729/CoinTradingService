package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.yesterdayVolatility.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.yesterdayVolatility.YesterdayVolatilityCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.YesterdayVolatilityCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class YesterdayVolatilityCalculatorService extends InvestmentCalculatorService<YesterdayVolatilityCalculator, YesterdayVolatilityCalculatorMapper, RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> {

    protected YesterdayVolatilityCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, YesterdayVolatilityCalculatorMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected YesterdayVolatilityCalculator createEntity(TradingBot tradingBot, RequestYesterdayVolatilityCalculatorDTO requestYesterdayVolatilityCalculatorDTO) {
        YesterdayVolatilityCalculator yesterdayVolatilityCalculator = new YesterdayVolatilityCalculator();
        yesterdayVolatilityCalculator.setTradingBot(tradingBot);

        updateEntity(yesterdayVolatilityCalculator, requestYesterdayVolatilityCalculatorDTO);

        return yesterdayVolatilityCalculator;
    }

    @Override
    protected void updateEntity(YesterdayVolatilityCalculator yesterdayVolatilityCalculator, RequestYesterdayVolatilityCalculatorDTO requestYesterdayVolatilityCalculatorDTO) {
        setParent(yesterdayVolatilityCalculator, requestYesterdayVolatilityCalculatorDTO.getParentId());
    }
}
