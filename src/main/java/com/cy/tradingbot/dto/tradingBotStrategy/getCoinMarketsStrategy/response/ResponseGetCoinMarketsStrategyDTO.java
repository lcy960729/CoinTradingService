package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class ResponseGetCoinMarketsStrategyDTO extends RepresentationModel<ResponseGetCoinMarketsStrategyDTO> {
    protected Long id;
    protected Long tradingBotId;
}
