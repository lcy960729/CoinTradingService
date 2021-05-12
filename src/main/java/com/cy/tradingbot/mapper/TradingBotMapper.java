package com.cy.tradingbot.mapper;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TradingBotMapper extends EntityMapper<ResponseTradingBotDTO, TradingBot> {


    @AfterMapping
    default void makeLinks(@MappingTarget ResponseTradingBotDTO responseTradingBotDTO) {
        responseTradingBotDTO.makeLinks();
    }
}
