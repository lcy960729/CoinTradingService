package com.cy.tradingbot.domain.orderProccesor.orderSheet;

import com.cy.tradingbot.domain.coin.CoinMarket;
import com.cy.tradingbot.domain.user.Credential;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private CoinMarket coinMarket;
    private long tradingBotId;
    private Credential credential;

    public String getCoinMarketName() {
        return coinMarket.getMarketName();
    }

    @Builder
    public OrderSheet(String side, Double volume, Double price, String orderType, CoinMarket coinMarket, long tradingBotId, Credential credential) {
        this.side = side;
        this.volume = volume;
        this.price = price;
        this.orderType = orderType;
        this.coinMarket = coinMarket;
        this.tradingBotId = tradingBotId;
        this.credential = credential;
    }

    public static OrderSheet createSellOrderSheet(Credential credential, CoinMarket coinMarket, double volume) {
        return OrderSheet.builder()
                .side(Side.ASK.getSide())
                .volume(volume)
                .orderType(OrdType.MARKET.getOrdType())
                .coinMarket(coinMarket)
                .credential(credential)
                .build();
    }

    public static OrderSheet createPurchaseOrderSheet(Credential credential, CoinMarket coinMarket, double price) {
        return OrderSheet.builder()
                .side(Side.BID.getSide())
                .price(price)
                .orderType(OrdType.PRICE.getOrdType())
                .coinMarket(coinMarket)
                .credential(credential)
                .build();
    }
}
