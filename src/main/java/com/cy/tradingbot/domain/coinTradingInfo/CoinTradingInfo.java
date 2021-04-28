package com.cy.tradingbot.domain.coinTradingInfo;

import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.CoinTradingInfoDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
public class CoinTradingInfo implements Comparable<CoinTradingInfo> {

    private final UUID id = UUID.randomUUID();
    private final String coinName;
    private final User orderer;
    private CoinTradingInfoStrategy coinTradingInfoStrategy;
    private double targetPrice = 0;
    private double currentPrice = 0;
    private double krwBalance;
    private Wallet wallet;
    private List<Double> movingAverageList;
    private List<Candle> candles;

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean stop() {
        return coinTradingInfoStrategy.canStop();
    }

    public void setCoinTradingInfoStrategy(CoinTradingInfoStrategy coinTradingInfoStrategy) {
        this.coinTradingInfoStrategy = coinTradingInfoStrategy;
    }

    public void setKrwBalance(double krwBalance) {
        this.krwBalance = krwBalance;
    }

    public void setCandles(List<Candle> candles) {
        this.candles = candles;
    }

    @Builder
    public CoinTradingInfo(String coinName, User orderer) {
        this.coinName = coinName;
        this.orderer = orderer;
    }

    public boolean canPurchaseIt() {
        if (orderer.getNumOfCanPurchase() <= 0)
            return false;

        return coinTradingInfoStrategy.canPurchaseIt(currentPrice, targetPrice);
    }

    public void updateCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public OrderSheet createSellOrderSheet() {
        return OrderSheet.builder()
                .coinTradingInfoId(id)
                .side(OrderSheet.Side.ASK.getSide())
                .volume(wallet.getBalance())
                .orderType(OrderSheet.OrdType.MARKET.getOrdType())
                .coinName(coinName)
                .credential(orderer.getCredential())
                .build();
    }

    public OrderSheet createPurchaseOrderSheet() {
        return OrderSheet.builder()
                .coinTradingInfoId(id)
                .side(OrderSheet.Side.BID.getSide())
                .price(getInvestment())
                .orderType(OrderSheet.OrdType.PRICE.getOrdType())
                .coinName(coinName)
                .credential(orderer.getCredential())
                .build();
    }

    private double getInvestment() {
        final double fee = 0.9994;

        return krwBalance * getScoreOfMovingAverage() * getVolatility() * fee;
    }

    private double getScoreOfMovingAverage() {
        double average = 0;
        for (Double ma : movingAverageList) {
            if (ma < currentPrice) average++;
        }

        average /= movingAverageList.size();
        return average;
    }

    private double getVolatility() {
        return 1 - (0.02 / getYesterdayVolatility() / orderer.getNumOfCanPurchase());
    }

    private double getYesterdayVolatility() {
        Candle candle = candles.get(1);
        return Math.abs(candle.getHighPrice() - candle.getLowPrice()) / currentPrice;
    }

    public void calcMovingAverage() {
        double sum = 0;
        List<Double> ret = new ArrayList<>();

        int numOfMovingAverageWindow = orderer.getTradingSettings().getNumOfMovingAverageWindow();

        for (int i = 1; i < numOfMovingAverageWindow; ++i) {
            sum += candles.get(candles.size() - i).getTradePrice();
        }

        for (int i = numOfMovingAverageWindow; i <= candles.size(); ++i) {
            sum += candles.get(candles.size() - i).getTradePrice();
            ret.add(sum / numOfMovingAverageWindow);
            sum -= candles.get(candles.size() - i + numOfMovingAverageWindow - 1).getTradePrice();
        }

        movingAverageList = ret;
    }

    public void calcTargetPrice() {
        if (candles.size() < 2) return;

        Candle todayCandle = candles.get(0);
        Candle yesterdayCandle = candles.get(1);

        double midPrice = yesterdayCandle.getHighPrice() - yesterdayCandle.getLowPrice();

        double k = candles.subList(1, candles.size()).stream()
                .mapToDouble(Candle::calcKValue)
                .average()
                .orElse(0);

        targetPrice = todayCandle.getOpeningPrice() + (midPrice * k);
    }

    public CoinTradingInfoDTO toDTO() {
        return CoinTradingInfoDTO.builder()
                .name(coinName)
                .currentPrice(String.format("%,.3f", currentPrice))
                .targetPrice(String.format("%,.3f", targetPrice))
                .purchasePrice(wallet == null ? "" : String.format("%,.3f", wallet.getAvgBuyPrice()))
                .build();
    }

    @Override
    public int compareTo(CoinTradingInfo o) {
        double purchasePrice = wallet == null ? 0 : wallet.getAvgBuyPrice();
        double purchasePrice2 = o.wallet == null ? 0 : o.wallet.getAvgBuyPrice();

        if (purchasePrice == 0 && purchasePrice2 == 0) {
            return Double.compare((targetPrice - currentPrice), (o.targetPrice - o.currentPrice));
        }

        return (int) (purchasePrice2 - purchasePrice);
    }
}
