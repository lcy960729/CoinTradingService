package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.fixedAmount;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FA")
@NoArgsConstructor
public class FixedAmountCalculator extends InvestmentCalculator {

    @Column
    private Double fixedAmount;

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    @Override
    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        return child.getInvestment(coinMarket, fixedAmount);
    }

    public double getFixedAmount() {
        return fixedAmount;
    }
}
