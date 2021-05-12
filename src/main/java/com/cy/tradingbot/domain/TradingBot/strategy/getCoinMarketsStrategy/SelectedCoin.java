package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.coin.Coin;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SelectedCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private SelectedCoinMarketsStrategy selectedCoinMarketsStrategy;

    @ManyToOne
    @JoinColumn
    private Coin coin;

    public SelectedCoinMarketsStrategy getSelectedCoinMarketsStrategy() {
        return selectedCoinMarketsStrategy;
    }

    public void setSelectedCoinMarketsStrategy(SelectedCoinMarketsStrategy selectedCoinMarketsStrategy) {
        this.selectedCoinMarketsStrategy = selectedCoinMarketsStrategy;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Long getId() {
        return id;
    }

    public String getMarketName(){
        return coin.getMarketName();
    }
}
