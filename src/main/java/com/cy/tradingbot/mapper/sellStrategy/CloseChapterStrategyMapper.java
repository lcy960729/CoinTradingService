package com.cy.tradingbot.mapper.sellStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CloseChapterStrategyMapper extends EntityMapper<ResponseCloseChapterStrategyDTO, CloseChapterStrategy> {

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    ResponseCloseChapterStrategyDTO toDTO(CloseChapterStrategy entity);

    @AfterMapping
    default void makeLinks(@MappingTarget ResponseCloseChapterStrategyDTO responseCloseChapterStrategyDTO) {
        responseCloseChapterStrategyDTO.makeLinks();
    }
}
