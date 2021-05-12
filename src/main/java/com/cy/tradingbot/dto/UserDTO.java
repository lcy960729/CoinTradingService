package com.cy.tradingbot.dto;

import com.cy.tradingbot.controller.tradingBot.TradingBotController;
import com.cy.tradingbot.controller.UserController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends RepresentationModel<UserDTO> {
    private String username;

    @JsonIgnore
    private String authority;

    public void makeLinks(){
        add(linkTo(methodOn(UserController.class).get(null)).withSelfRel());

        add(linkTo(methodOn(TradingBotController.class).getAll(null)).withRel("getAll-tradingBots"));
    }
}