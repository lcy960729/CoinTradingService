package com.cy.tradingbot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoinTradingInfoDTO {
    private String name;
    private String currentPrice;
    private String targetPrice;
    private String purchasePrice;

    @Builder
    public CoinTradingInfoDTO(String name, String currentPrice, String targetPrice, String purchasePrice) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;
        this.purchasePrice = purchasePrice;
    }
}
