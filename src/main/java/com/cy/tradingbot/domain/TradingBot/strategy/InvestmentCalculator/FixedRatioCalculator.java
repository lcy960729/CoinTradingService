package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator;


import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FR")
@NoArgsConstructor
public class FixedRatioCalculator extends InvestmentCalculator {

    @Column
    private Double fixedRatio;

    public void setFixedRatio(double fixedRatio) {
        this.fixedRatio = fixedRatio;
    }

    @Override
    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        return child.getInvestment(coinMarket, krwBalance * fixedRatio);
    }

    public double getFixedRatio() {
        return fixedRatio;
    }
}
