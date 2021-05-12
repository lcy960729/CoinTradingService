package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request;

import com.cy.tradingbot.dto.tradingBotStrategy.RequestTradingBotStrategyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor
@Getter
@Setter
public class RequestTopChangeRateCoinMarketsStrategyDTO extends RequestGetCoinMarketsStrategyDTO implements RequestTradingBotStrategyDTO {
    private Integer minOfPrice;
}
