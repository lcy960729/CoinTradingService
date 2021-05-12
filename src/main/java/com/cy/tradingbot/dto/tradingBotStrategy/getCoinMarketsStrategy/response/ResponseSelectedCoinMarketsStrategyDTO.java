package com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response;

import com.cy.tradingbot.controller.getCoinMarketsStrategy.SelectedCoinController;
import com.cy.tradingbot.controller.getCoinMarketsStrategy.SelectedCoinMarketsStrategyController;
import lombok.*;
import org.springframework.hateoas.LinkRelation;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseSelectedCoinMarketsStrategyDTO extends ResponseGetCoinMarketsStrategyDTO {

    private List<ResponseSelectedCoinDTO> selectedCoins = new ArrayList<>();

    public void makeLinks() {
        add(linkTo(methodOn(SelectedCoinMarketsStrategyController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(SelectedCoinMarketsStrategyController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-selectedCoinMarketsStrategy")));
        add(linkTo(methodOn(SelectedCoinMarketsStrategyController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-selectedCoinMarketsStrategy")));

        add(linkTo(methodOn(SelectedCoinController.class).create(null, tradingBotId, id, null)).withRel(LinkRelation.of("create-selectedCoinMarketsStrategy")));
    }
}
