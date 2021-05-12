package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.TradingBotStrategy;
import com.cy.tradingbot.domain.coin.coinExchange.CoinExchange;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(GetCoinMarketsStrategyEntityListener.class)
public abstract class GetCoinMarketsStrategy implements TradingBotStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn
    private TradingBot tradingBot;

    @Transient
    protected CoinExchange coinExchange;

    public void setCoinExchange(CoinExchange coinExchange) {
        this.coinExchange = coinExchange;
    }

    public abstract List<CoinMarket> getCoinMarkets();

    public Long getId() {
        return id;
    }

    public TradingBot getTradingBot() {
        return tradingBot;
    }

    public void setTradingBot(TradingBot tradingBot) {
        this.tradingBot = tradingBot;
    }

    public CoinExchange getCoinExchange() {
        return coinExchange;
    }
}
