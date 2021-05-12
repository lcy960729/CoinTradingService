package com.cy.tradingbot.controller.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestInvestmentCalculatorDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class InvestmentCalculatorController<K extends RequestInvestmentCalculatorDTO, V extends ResponseInvestmentCalculatorDTO> {

    private final InvestmentCalculatorService<K, V> investmentCalculatorService;

    public InvestmentCalculatorController(InvestmentCalculatorService<K, V> investmentCalculatorService) {
        this.investmentCalculatorService = investmentCalculatorService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> create(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @RequestBody K dto) {
        V result = investmentCalculatorService.create(tradingBotId, dto);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, tradingBotId, result.getId())).withSelfRel().toUri()).body(result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<V> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id, @RequestBody K dto) {
        return ResponseEntity.ok(investmentCalculatorService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<V> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        investmentCalculatorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<V> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(investmentCalculatorService.get(id));
    }
}
