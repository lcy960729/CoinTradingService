package com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response;

import com.cy.tradingbot.dto.tradingBotStrategy.RequestTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@NoArgsConstructor
public abstract class ResponseSellStrategyDTO extends RepresentationModel<ResponseSellStrategyDTO> implements ResponseTradingBotStrategyDTO {
    protected Long id;
    protected Long tradingBotId;
}
