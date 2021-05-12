package com.cy.tradingbot.controller.tradingBot;

import com.cy.tradingbot.domain.tradingBot.service.StartTradingBotService;
import com.cy.tradingbot.domain.tradingBot.service.StopTradingBotService;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.request.RequestTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDetailDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/v1/tradingBots", produces = MediaTypes.HAL_JSON_VALUE)
public class TradingBotController {

    private final TradingBotService tradingBotService;
    private final StartTradingBotService startTradingBotService;
    private final StopTradingBotService stopTradingBotService;

    public TradingBotController(TradingBotService tradingBotService, StartTradingBotService startTradingBotService, StopTradingBotService stopTradingBotService) {
        this.tradingBotService = tradingBotService;
        this.startTradingBotService = startTradingBotService;
        this.stopTradingBotService = stopTradingBotService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTradingBotDetailDTO> create(@AuthenticationPrincipal User user, @RequestBody RequestTradingBotDTO requestTradingBotDTO) {
        ResponseTradingBotDetailDTO result = tradingBotService.create(user, requestTradingBotDTO);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).get(user, result.getId())).withSelfRel().toUri()).body(result);
    }

    @GetMapping
    public ResponseEntity<List<ResponseTradingBotDTO>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(tradingBotService.getAll(user));
    }

    @PutMapping(path = "/{tradingBotId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTradingBotDetailDTO> update(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long id, @RequestBody RequestTradingBotDTO requestTradingBotDTO) {
        return ResponseEntity.ok(tradingBotService.update(user, id, requestTradingBotDTO));
    }

    @GetMapping(path = "/{tradingBotId}")
    public ResponseEntity<ResponseTradingBotDetailDTO> get(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long id) {
        return ResponseEntity.ok(tradingBotService.get(user, id));
    }

    @DeleteMapping(path = "/{tradingBotId}")
    public ResponseEntity<ResponseTradingBotDTO> delete(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long id) {
        tradingBotService.delete(user, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{tradingBotId}/start")
    public ResponseEntity<ResponseTradingBotDetailDTO> start(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long id) {
        startTradingBotService.start(user, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{tradingBotId}/stop")
    public ResponseEntity<ResponseTradingBotDTO> stop(@AuthenticationPrincipal User user, @PathVariable("tradingBotId") Long id) {
        stopTradingBotService.stop(user, id);
        return ResponseEntity.ok().build();
    }
}
