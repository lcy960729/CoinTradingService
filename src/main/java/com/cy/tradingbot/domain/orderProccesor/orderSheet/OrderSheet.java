package com.cy.tradingbot.domain.orderProccesor.orderSheet;

import com.cy.tradingbot.domain.user.Credential;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderSheet {
    public enum Side {
        ASK("ask"), BID("bid");

        private final String side;

        Side(String side) {
            this.side = side;
        }

        public String getSide() {
            return side;
        }
    }

    public enum OrdType {
        MARKET("market"), PRICE("price");

        private final String ordType;

        OrdType(String ordType) {
            this.ordType = ordType;
        }

        public String getOrdType() {
            return ordType;
        }
    }

    private String side;
    private Double volume;
    private Double price;
    private String orderType;
    private String coinName;
    private UUID coinTradingInfoId;
    private Credential credential;

    public String getCoinName() {
        return "KRW-" + coinName;
    }

    @Builder
    public OrderSheet(String side, Double volume, Double price, String orderType, String coinName, UUID coinTradingInfoId, Credential credential) {
        this.side = side;
        this.volume = volume;
        this.price = price;
        this.orderType = orderType;
        this.coinName = coinName;
        this.coinTradingInfoId = coinTradingInfoId;
        this.credential = credential;
    }
}
