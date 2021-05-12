package com.cy.tradingbot.dto.tradingBotStrategy;

import com.cy.tradingbot.controller.TradingBotController;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public interface ResponseTradingBotStrategyDTO {
    Long getId();
    void makeLinks();
}
