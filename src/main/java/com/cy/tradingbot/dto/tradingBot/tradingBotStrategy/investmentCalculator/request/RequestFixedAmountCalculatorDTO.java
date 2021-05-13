package com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request;

import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.RequestTradingBotStrategyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor
@Getter
@Setter
public class RequestFixedAmountCalculatorDTO extends RequestInvestmentCalculatorDTO implements RequestTradingBotStrategyDTO {
    private Double fixedAmount;
}
