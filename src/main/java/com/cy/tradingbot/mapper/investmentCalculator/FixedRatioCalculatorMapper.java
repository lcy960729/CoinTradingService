package com.cy.tradingbot.mapper.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedRatio.FixedRatioCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseFixedRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class FixedRatioCalculatorMapper implements EntityMapper<ResponseFixedRatioCalculatorDTO, FixedRatioCalculator> {
    @Lazy
    @Autowired
    private InvestmentCalculatorMapper investmentCalculatorMapper;

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.child", target = "child", qualifiedByName = "mapToInvestmentCalculatorDTO")
    @Mapping(source = "entity.parent.id", target = "parentId")
    public abstract ResponseFixedRatioCalculatorDTO toDTO(FixedRatioCalculator entity);

    @Named("mapToInvestmentCalculatorDTO")
    public ResponseInvestmentCalculatorDTO mapToInvestmentCalculatorDTO(InvestmentCalculator investmentCalculator) {
        return investmentCalculatorMapper.toDTO(investmentCalculator);
    }

    @AfterMapping
    public void makeLinks(@MappingTarget ResponseFixedRatioCalculatorDTO responseFixedRatioCalculatorDTO){
        responseFixedRatioCalculatorDTO.makeLinks();
    }
}
