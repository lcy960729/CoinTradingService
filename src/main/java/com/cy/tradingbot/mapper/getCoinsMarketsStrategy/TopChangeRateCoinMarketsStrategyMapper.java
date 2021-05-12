package com.cy.tradingbot.mapper.getCoinsMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseTopChangeRateCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopChangeRateCoinMarketsStrategyMapper extends EntityMapper<ResponseTopChangeRateCoinMarketsStrategyDTO, TopChangeRateCoinMarketsStrategy> {

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    ResponseTopChangeRateCoinMarketsStrategyDTO toDTO(TopChangeRateCoinMarketsStrategy entity);

    @AfterMapping
    default void makeLinks(@MappingTarget ResponseTopChangeRateCoinMarketsStrategyDTO responseTopChangeRateCoinMarketsStrategyDTO) {
        responseTopChangeRateCoinMarketsStrategyDTO.makeLinks();
    }
}
