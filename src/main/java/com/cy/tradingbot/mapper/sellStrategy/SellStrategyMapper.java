package com.cy.tradingbot.mapper.sellStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import org.springframework.stereotype.Component;

@Component
public class SellStrategyMapper {

    private final CloseChapterStrategyMapper closeChapterStrategyMapper;

    public SellStrategyMapper(CloseChapterStrategyMapper closeChapterStrategyMapper) {
        this.closeChapterStrategyMapper = closeChapterStrategyMapper;
    }

    public ResponseSellStrategyDTO toDTO(SellStrategy sellStrategy) {
        if (sellStrategy == null) return null;

        if (sellStrategy instanceof CloseChapterStrategy) {
            return closeChapterStrategyMapper.toDTO(((CloseChapterStrategy) sellStrategy));
        }

        return null;
    }
}