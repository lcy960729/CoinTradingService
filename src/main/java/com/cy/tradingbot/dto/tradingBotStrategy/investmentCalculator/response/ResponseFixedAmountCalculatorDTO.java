package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.controller.investmentCalculator.FixedAmountCalculatorController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseFixedAmountCalculatorDTO extends ResponseInvestmentCalculatorDTO {
    private double fixedAmount;

    public void makeLinks() {
        add(linkTo(methodOn(FixedAmountCalculatorController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(FixedAmountCalculatorController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-fixedAmountCalculator")));
        add(linkTo(methodOn(FixedAmountCalculatorController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-fixedAmountCalculator")));
    }
}
