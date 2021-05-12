package com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.TradingBotStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class PurchaseStrategy implements TradingBotStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private TradingBot tradingBot;

    public TradingBot getTradingBot() {
        return tradingBot;
    }

    public abstract void initialization();

    public abstract void finalization();

    public abstract void run(CoinMarket coinMarket);

    public void purchase(CoinMarket coinMarket) {
        tradingBot.purchaseSignal(coinMarket);
    }

    protected boolean isPurchasedCoin(CoinMarket coinMarket) {
        return tradingBot.isPurchasedCoin(coinMarket);
    }

    public void setTradingBot(TradingBot tradingBot) {
        this.tradingBot = tradingBot;
    }

    public Long getId() {
        return id;
    }
}
