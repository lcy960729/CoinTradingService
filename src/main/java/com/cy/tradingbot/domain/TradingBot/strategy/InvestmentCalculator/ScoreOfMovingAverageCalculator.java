package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator;

import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("S")
@NoArgsConstructor
public class ScoreOfMovingAverageCalculator extends InvestmentCalculator {

    @Column
    private Integer numOfMovingAverageWindow;

    @Override
    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        return child.getInvestment(coinMarket, krwBalance * getScoreOfMovingAverage(coinMarket));
    }

    public void setNumOfMovingAverageWindow(int numOfMovingAverageWindow) {
        this.numOfMovingAverageWindow = numOfMovingAverageWindow;
    }

    private double getScoreOfMovingAverage(CoinMarket coinMarket) {
        List<Double> movingAverageList = coinMarket.calcMovingAverage(numOfMovingAverageWindow);

        double average = 0;
        for (Double ma : movingAverageList) {
            if (ma < coinMarket.getCurrentPrice()) average++;
        }

        average /= movingAverageList.size();
        return average;
    }

    public int getNumOfMovingAverageWindow() {
        return numOfMovingAverageWindow;
    }
}
