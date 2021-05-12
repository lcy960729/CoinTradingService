package com.cy.tradingbot.mapper.getCoinsMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoin;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SelectedCoinMapper extends EntityMapper<ResponseSelectedCoinDTO, SelectedCoin> {

    @Override
    @Mapping(source = "entity.coin.coinName", target = "coinName")
    @Mapping(source = "entity.selectedCoinMarketsStrategy.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.selectedCoinMarketsStrategy.id", target = "strategyId")
    ResponseSelectedCoinDTO toDTO(SelectedCoin entity);

    @AfterMapping
    default void makeLinks(@MappingTarget ResponseSelectedCoinDTO responseSelectedCoinDTO) {
        responseSelectedCoinDTO.makeLinks();
    }
}
