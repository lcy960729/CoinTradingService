package com.cy.tradingbot.domain.user;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
public class TradingSettings {
    @Column(name = "maxOfCandles")
    private Integer maxOfCandles;

    @Column(name = "numOfMovingAverageWindow")
    private Integer numOfMovingAverageWindow;

    @Column(name = "coins")
    private String coins;

    @Column(name = "numOfCoinsForPurchase")
    private Integer numOfCoinsForPurchase;
}
