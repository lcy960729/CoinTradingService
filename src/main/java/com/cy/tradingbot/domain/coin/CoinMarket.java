package com.cy.tradingbot.domain.coin;

import com.cy.tradingbot.domain.coin.candle.Candle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CoinMarket {
    public CoinMarket(Coin coin) {
        this.coin = coin;
    }

    private Coin coin;

    private List<Candle> candles = new ArrayList<>();

    private double currentPrice = 0;

    public void updateCandles(List<Candle> candles) {
        this.candles = candles;
    }

    public List<Double> calcMovingAverage(int numOfMovingAverageWindow) {
        List<Double> ret = new ArrayList<>();

        double sum = 0;
        for (int i = 1; i < numOfMovingAverageWindow; ++i) {
            sum += candles.get(candles.size() - i).getTradePrice();
        }

        for (int i = numOfMovingAverageWindow; i <= candles.size(); ++i) {
            sum += candles.get(candles.size() - i).getTradePrice();
            ret.add(sum / numOfMovingAverageWindow);
            sum -= candles.get(candles.size() - i + numOfMovingAverageWindow - 1).getTradePrice();
        }

        return ret;
    }

    public double calcTargetPrice(int maxOfCandles) {
        Candle todayCandle = candles.get(0);
        Candle yesterdayCandle = candles.get(1);

        double midPrice = yesterdayCandle.getHighPrice() - yesterdayCandle.getLowPrice();

        double k = candles.subList(1, maxOfCandles).stream()
                .mapToDouble(Candle::calcKValue)
                .average()
                .orElse(0);

        return todayCandle.getOpeningPrice() + (midPrice * k);
    }

    public double getYesterdayVolatility() {
        Candle candle = candles.get(1);
        return Math.abs(candle.getHighPrice() - candle.getLowPrice()) / currentPrice;
    }

    public String getMarketName(){
        return coin.getMarketName();
    }

    public String getCoinName(){
        return coin.getCoinName();
    }
}