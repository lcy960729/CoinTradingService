package com.cy.tradingbot.dto.tradingBot.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Getter
@Setter
@NoArgsConstructor
public class RequestTradingBotDTO {
    private String name;
    private Double investmentRatio;
}
