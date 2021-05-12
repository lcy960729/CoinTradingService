package com.cy.tradingbot.mapper.getCoinsMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = SelectedCoinMapper.class)
public interface SelectedCoinMarketsStrategyMapper extends EntityMapper<ResponseSelectedCoinMarketsStrategyDTO, SelectedCoinMarketsStrategy> {

    @Override
    @Mapping(source = "entity.tradingBot.id", target = "tradingBotId")
    @Mapping(source = "entity.selectedCoins", target = "selectedCoins")
    ResponseSelectedCoinMarketsStrategyDTO toDTO(SelectedCoinMarketsStrategy entity);

    @AfterMapping
    default void makeLinks(@MappingTarget ResponseSelectedCoinMarketsStrategyDTO responseSelectedCoinMarketsStrategyDTO) {
        responseSelectedCoinMarketsStrategyDTO.makeLinks();
    }
}
