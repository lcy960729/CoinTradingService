package com.cy.tradingbot.mapper.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.ScoreOfMovingAverageCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseScoreOfMovingAverageCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class ScoreOfMovingAverageCalculatorMapper implements EntityMapper<ResponseScoreOfMovingAverageCalculatorDTO, ScoreOfMovingAverageCalculator> {
    @Lazy
    @Autowired
    private InvestmentCalculatorMapper investmentCalculatorMapper;

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.child", target = "child", qualifiedByName = "mapToInvestmentCalculatorDTO")
    @Mapping(source = "entity.parent.id", target = "parentId")
    public abstract ResponseScoreOfMovingAverageCalculatorDTO toDTO(ScoreOfMovingAverageCalculator entity);

    @Named("mapToInvestmentCalculatorDTO")
    public ResponseInvestmentCalculatorDTO mapToInvestmentCalculatorDTO(InvestmentCalculator investmentCalculator) {
        return investmentCalculatorMapper.toDTO(investmentCalculator);
    }

    @AfterMapping
    void makeLinks(@MappingTarget ResponseScoreOfMovingAverageCalculatorDTO responseScoreOfMovingAverageCalculatorDTO) {
        responseScoreOfMovingAverageCalculatorDTO.makeLinks();
    }
}
