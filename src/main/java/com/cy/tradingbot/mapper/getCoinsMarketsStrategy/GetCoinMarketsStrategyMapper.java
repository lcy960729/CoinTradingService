package com.cy.tradingbot.mapper.getCoinsMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import org.springframework.stereotype.Component;

@Component
public class GetCoinMarketsStrategyMapper {
    private final SelectedCoinMarketsStrategyMapper selectedCoinMarketsStrategyMapper;
    private final TopChangeRateCoinMarketsStrategyMapper topChangeRateCoinMarketsStrategyMapper;

    public GetCoinMarketsStrategyMapper(SelectedCoinMarketsStrategyMapper selectedCoinMarketsStrategyMapper, TopChangeRateCoinMarketsStrategyMapper topChangeRateCoinMarketsStrategyMapper) {
        this.selectedCoinMarketsStrategyMapper = selectedCoinMarketsStrategyMapper;
        this.topChangeRateCoinMarketsStrategyMapper = topChangeRateCoinMarketsStrategyMapper;
    }

    public ResponseGetCoinMarketsStrategyDTO toDTO(GetCoinMarketsStrategy getCoinMarketsStrategy) {
        if (getCoinMarketsStrategy == null)
            return null;

        if (getCoinMarketsStrategy instanceof SelectedCoinMarketsStrategy) {
            return selectedCoinMarketsStrategyMapper.toDTO(((SelectedCoinMarketsStrategy) getCoinMarketsStrategy));
        } else if (getCoinMarketsStrategy instanceof TopChangeRateCoinMarketsStrategy) {
            return topChangeRateCoinMarketsStrategyMapper.toDTO(((TopChangeRateCoinMarketsStrategy) getCoinMarketsStrategy));
        }

        return null;
    }
}
