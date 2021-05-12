package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.TradingBotStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor
public abstract class InvestmentCalculator implements TradingBotStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private TradingBot tradingBot;

    @OneToOne
    @JoinColumn
    protected InvestmentCalculator parent;

    @OneToOne(mappedBy = "parent")
    protected InvestmentCalculator child;

    public double getInvestment(CoinMarket coinMarket, double krwBalance) {
        final double fee = 0.9994;

        return child.getInvestment(coinMarket, krwBalance) * fee;
    }

    public void setChild(InvestmentCalculator child) {
        this.child = child;

        if (child != null) {
            child.setParent(this);
        }
    }

    public void setParent(InvestmentCalculator parent) {
        this.parent = parent;
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

    public InvestmentCalculator getParent() {
        return parent;
    }

    public InvestmentCalculator getChild() {
        return child;
    }
}
