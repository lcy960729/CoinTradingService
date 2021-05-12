package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator.YesterdayVolatilityCalculatorController;
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
public class ResponseYesterdayVolatilityCalculatorDTO extends ResponseInvestmentCalculatorDTO implements ResponseTradingBotStrategyDTO {
    public void makeLinks() {
        add(linkTo(methodOn(YesterdayVolatilityCalculatorController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(YesterdayVolatilityCalculatorController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-yesterdayVolatilityCalculator")));
        add(linkTo(methodOn(YesterdayVolatilityCalculatorController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-yesterdayVolatilityCalculator")));
    }
}
