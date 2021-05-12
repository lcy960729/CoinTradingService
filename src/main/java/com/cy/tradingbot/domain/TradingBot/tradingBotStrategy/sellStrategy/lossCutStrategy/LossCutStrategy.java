package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.lossCutStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("L")
public class LossCutStrategy extends SellStrategy {

    @Column
    private Double lossCutRatio;

    @Override
    public void finalization() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void run(CoinMarket coinMarket) {
        if (!getTradingBot().isPurchasedCoin(coinMarket)) return;

        double buyPrice = getTradingBot().getPriceOfPurchasedCoin(coinMarket);
        double currentPrice = coinMarket.getCurrentPrice();

        double ratio = (currentPrice - buyPrice) / buyPrice * 100;

        if (ratio <= -lossCutRatio) {
            sellSelectedCoin(coinMarket);
        }
    }
}
