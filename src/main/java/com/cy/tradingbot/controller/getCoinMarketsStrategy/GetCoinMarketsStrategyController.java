package com.cy.tradingbot.controller.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service.GetCoinMarketsStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GetCoinMarketsStrategyController<K extends RequestGetCoinMarketsStrategyDTO, V extends ResponseGetCoinMarketsStrategyDTO> {

    private final GetCoinMarketsStrategyService<K, V> getCoinMarketsStrategyService;

    public GetCoinMarketsStrategyController(GetCoinMarketsStrategyService<K, V> getCoinMarketsStrategyService) {
        this.getCoinMarketsStrategyService = getCoinMarketsStrategyService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> create(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @RequestBody K dto) {
        V result = getCoinMarketsStrategyService.create(tradingBotId, dto);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, tradingBotId, result.getId())).withSelfRel().toUri()).body(result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id, @RequestBody K dto) {
        return ResponseEntity.ok(getCoinMarketsStrategyService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<V> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        getCoinMarketsStrategyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<V> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(getCoinMarketsStrategyService.get(id));
    }
}
