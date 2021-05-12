package com.cy.tradingbot.domain.coin.candle;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Candle implements Comparable<Candle> {
    @JsonProperty("trade_price")
    private Double tradePrice;
    @JsonProperty("low_price")
    private Double lowPrice;
    @JsonProperty("high_price")
    private Double highPrice;
    @JsonProperty("opening_price")
    private Double openingPrice;
    @JsonProperty("acc_trade_price_24h")
    private Double accTradePrice24h;
    @JsonProperty("acc_trade_price")
    private Double accTradePrice;
    @JsonProperty("market")
    private String name;

    @JsonProperty("signed_change_rate")
    private Double signedChangeRate;

    private boolean isIncreased() {
        return highPrice - lowPrice > 0;
    }

    public double calcKValue() {
        return isIncreased() ? (1 - (Math.abs(openingPrice - tradePrice) / (highPrice - lowPrice))) : 0;
    }

    @Override
    public int compareTo(Candle o) {
        if (o.signedChangeRate.compareTo(signedChangeRate) == 0) {
            return o.accTradePrice24h.compareTo(accTradePrice24h);
        }

        return o.signedChangeRate.compareTo(signedChangeRate);
    }
}
