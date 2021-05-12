package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.coinRatio;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FR")
@NoArgsConstructor
public class CoinsRatioCalculator extends InvestmentCalculator {

    @Column
    private Integer numOfCoinsForPurchase;

    public void setNumOfCoinsForPurchase(int numOfCoinsForPurchase) {
        this.numOfCoinsForPurchase = numOfCoinsForPurchase;
    }

    @Override
    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        return child.getInvestment(coinMarket, krwBalance / (double) numOfCoinsForPurchase);
    }

    public int getNumOfCoinsForPurchase() {
        return numOfCoinsForPurchase;
    }
}
