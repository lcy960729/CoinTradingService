package com.cy.tradingbot.controller;

import com.cy.tradingbot.domain.TradingBot.strategy.service.TradingBotStrategyService;
import com.cy.tradingbot.domain.TradingBot.strategy.TradingBotStrategy;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.request.RequestTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class TradingBotStrategyController<Entity extends TradingBotStrategy, Repository extends JpaRepository<? super Entity, Long>, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestTradingBotDTO, ResponseDTO extends ResponseTradingBotDTO> {
    //    Entity extends TradingBotStrategy,
//    Repository extends JpaRepository<? super Entity, Long>,
//    Mapper extends EntityMapper<ResponseDTO, Entity>,
//    RequestDTO,
//    ResponseDTO
    private final TradingBotStrategyService<Entity, Repository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService;

    public TradingBotStrategyController(TradingBotStrategyService<Entity, Repository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService) {
        this.tradingBotStrategyService = tradingBotStrategyService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> create(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @RequestBody RequestDTO dto) {
        ResponseDTO result = tradingBotStrategyService.create(user, tradingBotId, dto);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, tradingBotId, result.getId())).withSelfRel().toUri()).body(result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id, @RequestBody RequestDTO dto) {
        return ResponseEntity.ok(tradingBotStrategyService.update(user, tradingBotId, id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long tradingBotId, @PathVariable("id") Long id) {
        tradingBotStrategyService.delete(user, tradingBotId, id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") long tradingBotId, @PathVariable("id") long id) {
        return ResponseEntity.ok(tradingBotStrategyService.get(user, tradingBotId, id));
    }
}
