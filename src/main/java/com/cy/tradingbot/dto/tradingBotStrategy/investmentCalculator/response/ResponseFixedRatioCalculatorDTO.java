package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator.FixedRatioCalculatorController;
import com.cy.tradingbot.dto.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseFixedRatioCalculatorDTO extends ResponseInvestmentCalculatorDTO implements ResponseTradingBotStrategyDTO {
    private double fixedRatio;

    public void makeLinks() {
        add(linkTo(methodOn(FixedRatioCalculatorController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(FixedRatioCalculatorController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-fixedRatioCalculator")));
        add(linkTo(methodOn(FixedRatioCalculatorController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-fixedRatioCalculator")));
    }
}
