package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator;

import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("YV")
@NoArgsConstructor
public class YesterdayVolatilityCalculator extends InvestmentCalculator {
    @Override
    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        return child.getInvestment(coinMarket, krwBalance * coinMarket.getYesterdayVolatility());
    }
}
