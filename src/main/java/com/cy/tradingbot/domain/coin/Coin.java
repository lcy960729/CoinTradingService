package com.cy.tradingbot.domain.coin;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.selcetedCoin.SelectedCoin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Coin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Column(name = "coinName", unique = true)
    private String coinName;

    @JsonProperty("market")
    @Column(name = "marketName", unique = true)
    private String marketName;

    public void setMarketName(String marketName) {
        this.marketName = marketName;

        coinName = marketName.substring(4);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "coin")
    private Set<SelectedCoin> selectedCoins;

    public void addCoin(SelectedCoin selectedCoin) {
        selectedCoins.add(selectedCoin);
    }
}
