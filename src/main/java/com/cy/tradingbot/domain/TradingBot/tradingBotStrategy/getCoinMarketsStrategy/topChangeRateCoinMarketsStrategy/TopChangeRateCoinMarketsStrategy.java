package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("T")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TopChangeRateCoinMarketsStrategy extends GetCoinMarketsStrategy {

    @Column
    private Integer minOfPrice = 0;

    public void setMinOfPrice(int minOfPrice) {
        this.minOfPrice = minOfPrice;
    }

    @Override
    public List<CoinMarket> getCoinMarkets() {
        return coinExchange.getCoinMarkets().stream()
                .filter(coinMarket -> coinMarket.getCurrentPrice() > minOfPrice)
                .collect(Collectors.toList()).subList(0, 10);
    }

    public Integer getMinOfPrice() {
        return minOfPrice;
    }
}
