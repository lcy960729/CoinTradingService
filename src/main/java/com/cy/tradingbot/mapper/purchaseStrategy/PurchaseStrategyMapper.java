package com.cy.tradingbot.mapper.purchaseStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.PurchaseStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.volatilityBreakoutStrategy.VolatilityBreakoutStrategy;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import org.springframework.stereotype.Component;

@Component
public class PurchaseStrategyMapper {

    private final VolatilityBreakoutStrategyMapper volatilityBreakoutStrategyMapper;

    public PurchaseStrategyMapper(VolatilityBreakoutStrategyMapper volatilityBreakoutStrategyMapper) {
        this.volatilityBreakoutStrategyMapper = volatilityBreakoutStrategyMapper;
    }

    public ResponsePurchaseStrategyDTO toDTO(PurchaseStrategy purchaseStrategy) {
        if (purchaseStrategy == null) return null;

        if (purchaseStrategy instanceof VolatilityBreakoutStrategy) {
            return volatilityBreakoutStrategyMapper.toDTO(((VolatilityBreakoutStrategy) purchaseStrategy));
        }

        return null;
    }
}
