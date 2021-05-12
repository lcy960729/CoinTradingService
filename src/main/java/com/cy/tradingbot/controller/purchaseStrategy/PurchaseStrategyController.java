package com.cy.tradingbot.controller.purchaseStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.service.PurchaseStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestPurchaseStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PurchaseStrategyController<K extends RequestPurchaseStrategyDTO, V extends ResponsePurchaseStrategyDTO> {

    private final PurchaseStrategyService<K, V> purchaseStrategyService;

    public PurchaseStrategyController(PurchaseStrategyService<K, V> purchaseStrategyService) {
        this.purchaseStrategyService = purchaseStrategyService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> create(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @RequestBody K dto) {
        V result = purchaseStrategyService.create(tradingBotId, dto);

        return  ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, tradingBotId, result.getId())).withSelfRel().toUri()).body(result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id, @RequestBody K dto) {
        return ResponseEntity.ok(purchaseStrategyService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<V> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        purchaseStrategyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<V> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(purchaseStrategyService.get(id));
    }
}
