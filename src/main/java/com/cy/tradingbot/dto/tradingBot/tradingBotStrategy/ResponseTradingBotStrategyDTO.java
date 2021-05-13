package com.cy.tradingbot.dto.tradingBot.tradingBotStrategy;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public interface ResponseTradingBotStrategyDTO {
    Long getId();
    void makeLinks();
}
