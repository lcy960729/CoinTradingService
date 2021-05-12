package com.cy.tradingbot.mapper.purchaseStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.volatilityBreakoutStrategy.VolatilityBreakoutStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VolatilityBreakoutStrategyMapper extends EntityMapper<ResponseVolatilityBreakoutStrategyDTO, VolatilityBreakoutStrategy> {

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    ResponseVolatilityBreakoutStrategyDTO toDTO(VolatilityBreakoutStrategy entity);

    @AfterMapping
    default void makeLinks(@MappingTarget ResponseVolatilityBreakoutStrategyDTO responseVolatilityBreakoutStrategyDTO){
        responseVolatilityBreakoutStrategyDTO.makeLinks();
    }
}
