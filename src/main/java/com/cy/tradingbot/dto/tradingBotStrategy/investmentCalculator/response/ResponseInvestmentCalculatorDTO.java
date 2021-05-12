package com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response;

import com.cy.tradingbot.dto.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public abstract class ResponseInvestmentCalculatorDTO extends RepresentationModel<ResponseInvestmentCalculatorDTO> implements ResponseTradingBotStrategyDTO {
    protected Long id;
    protected Long tradingBotId;
    protected ResponseInvestmentCalculatorDTO child;
    protected Long parentId;
}
