package com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request;

import com.cy.tradingbot.dto.tradingBotStrategy.RequestTradingBotStrategyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor
@Getter
@Setter
public class RequestVolatilityBreakoutStrategyDTO extends RequestPurchaseStrategyDTO implements RequestTradingBotStrategyDTO {
    private Integer maxOfCandles;
}
