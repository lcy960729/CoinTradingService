package com.cy.tradingbot.controller.sellStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service.SellStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestSellStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class SellStrategyController<K extends RequestSellStrategyDTO, V extends ResponseSellStrategyDTO> {

    private final SellStrategyService<K, V> sellStrategyService;

    public SellStrategyController(SellStrategyService<K, V> sellStrategyService) {
        this.sellStrategyService = sellStrategyService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> create(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @RequestBody K dto) {
        V result = sellStrategyService.create(tradingBotId, dto);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, tradingBotId, result.getId())).withSelfRel().toUri()).body(result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id, @RequestBody K dto) {
        return ResponseEntity.ok(sellStrategyService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<V> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        sellStrategyService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<V> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") long tradingBotId, @PathVariable("id") long id) {
        return ResponseEntity.ok(sellStrategyService.get(id));
    }
}
