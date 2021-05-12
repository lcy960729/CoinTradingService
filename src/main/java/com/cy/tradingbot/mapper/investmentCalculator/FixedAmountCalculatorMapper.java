package com.cy.tradingbot.mapper.investmentCalculator;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedAmount.FixedAmountCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseFixedAmountCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class FixedAmountCalculatorMapper implements EntityMapper<ResponseFixedAmountCalculatorDTO, FixedAmountCalculator> {

    @Lazy
    @Autowired
    private InvestmentCalculatorMapper investmentCalculatorMapper;

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.child", target = "child", qualifiedByName = "mapToInvestmentCalculatorDTO")
    @Mapping(source = "entity.parent.id", target = "parentId")
    public abstract ResponseFixedAmountCalculatorDTO toDTO(FixedAmountCalculator entity);

    @Named("mapToInvestmentCalculatorDTO")
    public ResponseInvestmentCalculatorDTO mapToInvestmentCalculatorDTO(InvestmentCalculator investmentCalculator) {
        return investmentCalculatorMapper.toDTO(investmentCalculator);
    }

    @AfterMapping
    public void makeLinks(@MappingTarget ResponseFixedAmountCalculatorDTO responseFixedAmountCalculatorDTO) {
        responseFixedAmountCalculatorDTO.makeLinks();
    }
}
