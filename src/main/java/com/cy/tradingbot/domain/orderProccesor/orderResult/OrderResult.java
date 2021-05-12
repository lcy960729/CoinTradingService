package com.cy.tradingbot.domain.orderProccesor.orderResult;

import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import com.cy.tradingbot.domain.user.Credential;
import com.cy.tradingbot.util.TimeCalculator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResult {
    @JsonProperty("state")
    private String state;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("created_at")
    private LocalDateTime created_at;

    private OrderSheet orderSheet;

    public boolean isBuying() {
        return orderSheet.getSide().equals("bid");
    }

    public boolean isDone() {
        return state.equals("cancel") || state.equals("done");
    }

    public void setCreated_at(String created_at) {
        this.created_at = LocalDateTime.parse(created_at.substring(0, created_at.length() - 6));
    }

    public boolean is10secondsAfterOrder() {
        return Duration.between(created_at, TimeCalculator.now()).abs().getSeconds() > 10;
    }

    public String getCoinName(){
        return orderSheet.getCoinMarketName();
    }

    public Credential getCredential(){
        return orderSheet.getCredential();
    }

    public CoinMarket getCoin(){
        return orderSheet.getCoinMarket();
    }
}
