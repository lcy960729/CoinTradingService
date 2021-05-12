package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response;

import com.cy.tradingbot.controller.getCoinMarketsStrategy.TopChangeRateCoinMarketsStrategyController;
import lombok.*;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseTopChangeRateCoinMarketsStrategyDTO extends ResponseGetCoinMarketsStrategyDTO {
    private int minOfPrice;

    public void makeLinks() {
        add(linkTo(methodOn(TopChangeRateCoinMarketsStrategyController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(TopChangeRateCoinMarketsStrategyController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-topChangeRateCoinMarketsStrategy")));
        add(linkTo(methodOn(TopChangeRateCoinMarketsStrategyController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-topChangeRateCoinMarketsStrategy")));
    }
}
