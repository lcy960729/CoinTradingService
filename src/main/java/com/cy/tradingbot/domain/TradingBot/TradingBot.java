package com.cy.tradingbot.domain.TradingBot;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.PurchaseStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.exception.NotFoundEntityException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@EntityListeners(TradingBotEntityListener.class)
@NoArgsConstructor
public class TradingBot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private double investmentRatio;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column
    private Boolean isRunning;

    @OneToOne(mappedBy = "tradingBot")
    private GetCoinMarketsStrategy getCoinMarketsStrategy;

    @OneToMany(mappedBy = "tradingBot")
    private List<PurchaseStrategy> purchaseStrategy;

    @OneToMany(mappedBy = "tradingBot")
    private List<SellStrategy> sellStrategy;

    @OneToMany(mappedBy = "tradingBot")
    private List<InvestmentCalculator> investmentCalculators;

    @Transient
    private Map<CoinMarket, Double> purchasedCoins = new HashMap<>();

    @Transient
    private double krwBalance;

    @Transient
    private TradingSignalObserver tradingSignalObserver;

    public void initialization(double krwBalance) {
        this.krwBalance = krwBalance * investmentRatio;
        isRunning = true;

        purchasedCoins.clear();

        purchaseStrategy.forEach(PurchaseStrategy::initialization);
        sellStrategy.forEach(SellStrategy::initialize);
    }

    public void finalization() {
        this.krwBalance = 0;
        isRunning = false;

        purchasedCoins.clear();

        purchaseStrategy.forEach(PurchaseStrategy::finalization);
        sellStrategy.forEach(SellStrategy::finalization);
    }

    public void trade() {
        runPurchaseStrategy();
        runSellStrategy();
    }

    private void runPurchaseStrategy() {
        List<CoinMarket> coinMarkets = getGetCoinMarketsStrategy().getCoinMarkets();
        for (CoinMarket coinMarket : coinMarkets) {
            for (PurchaseStrategy strategy : purchaseStrategy) {
                strategy.run(coinMarket);
            }
        }
    }

    private void runSellStrategy() {
        for (CoinMarket coinMarket : purchasedCoins.keySet()) {
            for (SellStrategy strategy : sellStrategy) {
                strategy.run(coinMarket);
            }
        }
    }

    public InvestmentCalculator getInvestmentCalculator() {
        return investmentCalculators.stream().filter(calculator -> calculator.getParent() == null).findFirst().orElseThrow(NotFoundEntityException::new);
    }

    private double getPrice(CoinMarket coinMarket) {
        return getInvestmentCalculator().getInvestment(coinMarket, krwBalance);
    }

    public boolean isPurchasedCoin(CoinMarket coinMarket) {
        return purchasedCoins.containsKey(coinMarket);
    }

    public void addPurchasedCoin(CoinMarket coinMarket, double volume) {
        purchasedCoins.put(coinMarket, volume);
    }

    public void removePurchasedCoin(CoinMarket coinMarket) {
        purchasedCoins.remove(coinMarket);
    }

    public double getPriceOfPurchasedCoin(CoinMarket coinMarket) {
        return purchasedCoins.get(coinMarket);
    }

    private double getVolumeOfPurchasedCoin(CoinMarket coinMarket) {
        return purchasedCoins.get(coinMarket);
    }

    public void purchaseSignal(CoinMarket coinMarket) {
        OrderSheet orderSheet = createPurchaseOrderSheet(coinMarket);

        if (orderSheet.getPrice() < 5000) return;

        tradingSignalObserver.update(orderSheet);
    }

    public void sellSignal(CoinMarket coinMarket) {
        tradingSignalObserver.update(createSellOrderSheet(coinMarket));
    }

    public void sellPurchasedAllCoins() {
        purchasedCoins.keySet().forEach(this::sellSignal);
    }

    private OrderSheet createSellOrderSheet(CoinMarket coinMarket) {
        return OrderSheet.createSellOrderSheet(user.getCredential(), coinMarket, getVolumeOfPurchasedCoin(coinMarket));
    }

    private OrderSheet createPurchaseOrderSheet(CoinMarket coinMarket) {
        return OrderSheet.createPurchaseOrderSheet(user.getCredential(), coinMarket, getPrice(coinMarket));
    }
}