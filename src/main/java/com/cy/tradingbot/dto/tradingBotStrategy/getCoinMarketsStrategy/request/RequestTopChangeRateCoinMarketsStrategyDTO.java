package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor
@Getter
@Setter
public class RequestTopChangeRateCoinMarketsStrategyDTO extends RequestGetCoinMarketsStrategyDTO {
    private Integer minOfPrice;
}
