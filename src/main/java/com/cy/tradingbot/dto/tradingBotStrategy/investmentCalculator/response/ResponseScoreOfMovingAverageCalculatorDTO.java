package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.controller.investmentCalculator.ScoreOfMovingAverageCalculatorController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseScoreOfMovingAverageCalculatorDTO extends ResponseInvestmentCalculatorDTO {
    private int numOfMovingAverageWindow;

    public void makeLinks() {
        add(linkTo(methodOn(ScoreOfMovingAverageCalculatorController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(ScoreOfMovingAverageCalculatorController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-scoreOfMovingAverageCalculator")));
        add(linkTo(methodOn(ScoreOfMovingAverageCalculatorController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-scoreOfMovingAverageCalculator")));
    }
}
