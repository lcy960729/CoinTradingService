package com.cy.tradingbot.mapper.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.YesterdayVolatilityCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseYesterdayVolatilityCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class YesterdayVolatilityCalculatorMapper implements EntityMapper<ResponseYesterdayVolatilityCalculatorDTO, YesterdayVolatilityCalculator> {
    @Lazy
    @Autowired
    private InvestmentCalculatorMapper investmentCalculatorMapper;

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.child", target = "child", qualifiedByName = "mapToInvestmentCalculatorDTO")
    @Mapping(source = "entity.parent.id", target = "parentId")
    public abstract ResponseYesterdayVolatilityCalculatorDTO toDTO(YesterdayVolatilityCalculator entity);

    @Named("mapToInvestmentCalculatorDTO")
    public ResponseInvestmentCalculatorDTO mapToInvestmentCalculatorDTO(InvestmentCalculator investmentCalculator) {
        return investmentCalculatorMapper.toDTO(investmentCalculator);
    }

    @AfterMapping
    void makeLinks(@MappingTarget ResponseYesterdayVolatilityCalculatorDTO responseYesterdayVolatilityCalculatorDTO) {
        responseYesterdayVolatilityCalculatorDTO.makeLinks();
    }
}
