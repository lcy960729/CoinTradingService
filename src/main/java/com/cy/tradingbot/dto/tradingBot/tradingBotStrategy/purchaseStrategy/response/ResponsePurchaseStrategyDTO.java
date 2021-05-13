package com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.response;

import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public abstract class ResponsePurchaseStrategyDTO extends RepresentationModel<ResponsePurchaseStrategyDTO> implements ResponseTradingBotStrategyDTO {
    protected Long id;
    protected Long tradingBotId;
}