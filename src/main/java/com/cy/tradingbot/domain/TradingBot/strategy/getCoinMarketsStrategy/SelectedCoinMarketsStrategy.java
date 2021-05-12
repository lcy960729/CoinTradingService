package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@DiscriminatorValue("S")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SelectedCoinMarketsStrategy extends GetCoinMarketsStrategy {

    @OneToMany(mappedBy = "selectedCoinMarketsStrategy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SelectedCoin> selectedCoins = new HashSet<>();

    @Override
    public List<CoinMarket> getCoinMarkets() {
        return selectedCoins.stream()
                .map(selectedCoin -> coinExchange.getCoinMarket(selectedCoin.getMarketName()))
                .collect(Collectors.toList());
    }

    public void addCoin(SelectedCoin selectedCoin) {
        selectedCoins.add(selectedCoin);
    }

    public void remove(SelectedCoin selectedCoin) {
        selectedCoins.remove(selectedCoin);
    }

    public boolean isSelected(Long coinId) {
        return selectedCoins.contains(coinId);
    }

    public Set<SelectedCoin> getSelectedCoins() {
        return selectedCoins;
    }
}
