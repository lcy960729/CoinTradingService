package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.TradingBotStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class SellStrategy implements TradingBotStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private TradingBot tradingBot;

    public abstract void initialize();

    public abstract void finalization();

    public abstract void run(CoinMarket coinMarket);

    protected void sellSelectedCoin(CoinMarket coinMarket) {
        tradingBot.sellSignal(coinMarket);
    }

    protected void sellPurchasedCoin() {
        tradingBot.getPurchasedCoins().keySet()
                .forEach(coinMarket -> tradingBot.sellSignal(coinMarket));
    }

    public void setTradingBot(TradingBot tradingBot) {
        this.tradingBot = tradingBot;
    }

    public TradingBot getTradingBot() {
        return tradingBot;
    }

    public Long getId() {
        return id;
    }
}
