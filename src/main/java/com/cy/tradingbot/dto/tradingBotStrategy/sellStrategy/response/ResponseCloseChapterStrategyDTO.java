package com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response;

import com.cy.tradingbot.controller.sellStrategy.CloseChapterStrategyController;
import lombok.*;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class ResponseCloseChapterStrategyDTO extends ResponseSellStrategyDTO {

    @Override
    public void makeLinks() {
        add(linkTo(methodOn(CloseChapterStrategyController.class).get(null, tradingBotId, id)).withSelfRel());

        add(linkTo(methodOn(CloseChapterStrategyController.class).update(null, tradingBotId, id, null)).withRel(LinkRelation.of("update-closeChapterStrategy")));
        add(linkTo(methodOn(CloseChapterStrategyController.class).delete(null, tradingBotId, id)).withRel(LinkRelation.of("delete-closeChapterStrategy")));
    }
}
