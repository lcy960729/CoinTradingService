package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor
@Getter
@Setter
public class RequestSelectedCoinMarketsStrategyDTO extends RequestGetCoinMarketsStrategyDTO {

    private List<RequestSelectedCoinDTO> requestSelectedCoinDTOList = new ArrayList<>();

}
