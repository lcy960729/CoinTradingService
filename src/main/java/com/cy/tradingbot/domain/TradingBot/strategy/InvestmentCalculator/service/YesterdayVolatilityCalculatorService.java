package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.YesterdayVolatilityCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.investmentCalculator.YesterdayVolatilityCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class YesterdayVolatilityCalculatorService extends InvestmentCalculatorService<RequestYesterdayVolatilityCalculatorDTO, ResponseYesterdayVolatilityCalculatorDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final YesterdayVolatilityCalculatorMapper yesterdayVolatilityCalculatorMapper;

    public YesterdayVolatilityCalculatorService(TradingBotRepository tradingBotRepository, InvestmentCalculatorRepository investmentCalculatorRepository, YesterdayVolatilityCalculatorMapper yesterdayVolatilityCalculatorMapper) {
        super(investmentCalculatorRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.yesterdayVolatilityCalculatorMapper = yesterdayVolatilityCalculatorMapper;
    }

    public ResponseYesterdayVolatilityCalculatorDTO create(long tradingId, RequestYesterdayVolatilityCalculatorDTO yesterdayVolatilityCalculatorDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingId).orElseThrow(NotFoundEntityException::new);

        YesterdayVolatilityCalculator yesterdayVolatilityCalculator = new YesterdayVolatilityCalculator();
        yesterdayVolatilityCalculator.setTradingBot(tradingBot);

        setParent(yesterdayVolatilityCalculator, yesterdayVolatilityCalculatorDTO.getParentId());

        yesterdayVolatilityCalculator = investmentCalculatorRepository.save(yesterdayVolatilityCalculator);

        return yesterdayVolatilityCalculatorMapper.toDTO(yesterdayVolatilityCalculator);
    }

    @Override
    public ResponseYesterdayVolatilityCalculatorDTO update(long investmentCalculatorId, RequestYesterdayVolatilityCalculatorDTO yesterdayVolatilityCalculatorDTO) {
        YesterdayVolatilityCalculator yesterdayVolatilityCalculator = (YesterdayVolatilityCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new);

        setParent(yesterdayVolatilityCalculator, yesterdayVolatilityCalculatorDTO.getParentId());

        yesterdayVolatilityCalculator = investmentCalculatorRepository.save(yesterdayVolatilityCalculator);

        return yesterdayVolatilityCalculatorMapper.toDTO(yesterdayVolatilityCalculator);
    }

    @Override
    public ResponseYesterdayVolatilityCalculatorDTO get(long investmentCalculatorId) {
        YesterdayVolatilityCalculator yesterdayVolatilityCalculator = ((YesterdayVolatilityCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));
        return yesterdayVolatilityCalculatorMapper.toDTO(yesterdayVolatilityCalculator);
    }
}
