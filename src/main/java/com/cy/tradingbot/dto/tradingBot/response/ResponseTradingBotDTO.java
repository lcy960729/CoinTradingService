package com.cy.tradingbot.dto.tradingBot.response;

import com.cy.tradingbot.controller.tradingBot.TradingBotController;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class ResponseTradingBotDTO extends RepresentationModel<ResponseTradingBotDTO> {
    private Long id;
    private String name;
    private Boolean isRunning;

    public void makeLinks() {
        add(linkTo(methodOn(TradingBotController.class).getAll(null)).withSelfRel());

        add(linkTo(methodOn(TradingBotController.class).get(null, id)).withRel("get-tradingBot"));
    }
}
