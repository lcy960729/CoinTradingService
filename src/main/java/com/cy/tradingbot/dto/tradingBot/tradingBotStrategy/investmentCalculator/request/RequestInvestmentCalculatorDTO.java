package com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request;

import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.RequestTradingBotStrategyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestInvestmentCalculatorDTO implements RequestTradingBotStrategyDTO {
    protected Long parentId;
}
