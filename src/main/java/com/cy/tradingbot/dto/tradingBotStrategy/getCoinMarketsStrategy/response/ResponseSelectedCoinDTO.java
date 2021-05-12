package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response;

import com.cy.tradingbot.controller.getCoinMarketsStrategy.SelectedCoinController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
public class ResponseSelectedCoinDTO extends RepresentationModel<ResponseSelectedCoinDTO> {
    private Long id;
    private String CoinName;

    @JsonIgnore
    private Long tradingBotId;
    @JsonIgnore
    private Long strategyId;

    public void makeLinks() {
        add(linkTo(methodOn(SelectedCoinController.class).get(null, tradingBotId, strategyId, id)).withSelfRel());

        add(linkTo(methodOn(SelectedCoinController.class).delete(null, tradingBotId, strategyId, id)).withRel(LinkRelation.of("delete-selectedCoinMarketsStrategy")));
    }
}
