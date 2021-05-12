package com.cy.tradingbot.mapper.getCoinsMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
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
