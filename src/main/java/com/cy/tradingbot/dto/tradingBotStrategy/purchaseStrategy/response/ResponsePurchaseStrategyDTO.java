package com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class ResponsePurchaseStrategyDTO extends RepresentationModel<ResponsePurchaseStrategyDTO> {
    protected Long id;
    protected Long tradingBotId;
}