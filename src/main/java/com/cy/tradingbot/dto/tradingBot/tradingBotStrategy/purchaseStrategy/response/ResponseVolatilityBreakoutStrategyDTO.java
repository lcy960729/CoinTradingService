package com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.response;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.purchaseStrategy.VolatilityBreakoutStrategyController;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import lombok.*;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseVolatilityBreakoutStrategyDTO extends ResponsePurchaseStrategyDTO implements ResponseTradingBotStrategyDTO {
    private int maxOfCandles;

    public void makeLinks() {
        add(linkTo(methodOn(VolatilityBreakoutStrategyController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(VolatilityBreakoutStrategyController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-volatilityBreakoutStrategy")));
        add(linkTo(methodOn(VolatilityBreakoutStrategyController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-volatilityBreakoutStrategy")));
    }
}
