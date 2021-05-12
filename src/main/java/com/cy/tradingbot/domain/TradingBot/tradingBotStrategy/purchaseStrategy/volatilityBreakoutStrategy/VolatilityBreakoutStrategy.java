package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.volatilityBreakoutStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.PurchaseStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.util.TimeCalculator;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("V")
public class VolatilityBreakoutStrategy extends PurchaseStrategy {
    @Column
    private Integer maxOfCandles;

    @Transient
    private LocalDateTime closingTime;

    @Transient
    private final Map<CoinMarket, Double> targetPrices = new HashMap<>();

    @Override
    public void initialization() {
        targetPrices.clear();
        closingTime = TimeCalculator.closingTime();
    }

    @Override
    public void finalization() {
        targetPrices.clear();
        closingTime = null;
    }

    private void closingChapter() {
        if (TimeCalculator.now().isBefore(closingTime)) return;

        closingTime = TimeCalculator.closingTime();
        targetPrices.clear();
    }

    @Override
    public void run(CoinMarket coinMarket) {
        if (isPurchasedCoin(coinMarket)) return;

        closingChapter();

        if (!targetPrices.containsKey(coinMarket)) {
            updateTargetPriceAndMovingAverage(coinMarket);
        }

        double currentPrice = coinMarket.getCurrentPrice();

        if (targetPrices.get(coinMarket) <= currentPrice) {
            purchase(coinMarket);
        }
    }

    private void updateTargetPriceAndMovingAverage(CoinMarket coinMarket) {
        targetPrices.put(coinMarket, coinMarket.calcTargetPrice(maxOfCandles));
    }

    public void setMaxOfCandles(int maxOfCandles) {
        this.maxOfCandles = maxOfCandles;
    }

    public Integer getMaxOfCandles() {
        return maxOfCandles;
    }
}
