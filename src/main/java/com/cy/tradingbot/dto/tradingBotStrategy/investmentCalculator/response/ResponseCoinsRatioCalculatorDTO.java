package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.controller.investmentCalculator.CoinsRatioCalculatorController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseCoinsRatioCalculatorDTO extends ResponseInvestmentCalculatorDTO {
    private int numOfCoinsForPurchase;

    public void makeLinks() {
        add(linkTo(methodOn(CoinsRatioCalculatorController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(CoinsRatioCalculatorController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-coinsRatioCalculator")));
        add(linkTo(methodOn(CoinsRatioCalculatorController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-coinsRatioCalculator")));
    }
}
