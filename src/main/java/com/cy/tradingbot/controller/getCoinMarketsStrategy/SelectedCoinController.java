package com.cy.tradingbot.controller.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service.SelectedCoinService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/v1/tradingBots/{tradingBotId}/selectedCoinMarketsStrategies/{selectedCoinMarketsStrategyId}/selectedCoins", produces = MediaTypes.HAL_JSON_VALUE)
public class SelectedCoinController {

    private final SelectedCoinService selectedCoinService;

    public SelectedCoinController(SelectedCoinService selectedCoinService) {
        this.selectedCoinService = selectedCoinService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSelectedCoinDTO> create(@AuthenticationPrincipal User user,
                                                          @PathVariable("tradingBotId") Long tradingBotId,
                                                          @PathVariable("selectedCoinMarketsStrategyId") Long selectedCoinMarketsStrategyId,
                                                          @RequestBody RequestSelectedCoinDTO requestSelectedCoinDTO) {

        ResponseSelectedCoinDTO result = selectedCoinService.create(selectedCoinMarketsStrategyId, requestSelectedCoinDTO);

        URI uri = linkTo(methodOn(this.getClass()).get(user, tradingBotId, selectedCoinMarketsStrategyId, result.getId())).withSelfRel().toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSelectedCoinDTO>> getAll(@AuthenticationPrincipal User user,
                                                                @PathVariable("tradingBotId") Long tradingBotId,
                                                                @PathVariable("selectedCoinMarketsStrategyId") Long selectedCoinMarketsStrategyId) {

        return ResponseEntity.ok(selectedCoinService.getAll(selectedCoinMarketsStrategyId));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseSelectedCoinDTO> get(@AuthenticationPrincipal User user,
                                                       @PathVariable("tradingBotId") Long tradingBotId,
                                                       @PathVariable("selectedCoinMarketsStrategyId") Long selectedCoinMarketsStrategyId,
                                                       @PathVariable("id") Long id) {

        return ResponseEntity.ok(selectedCoinService.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseTradingBotDTO> delete(@AuthenticationPrincipal User user,
                                                        @PathVariable("tradingBotId") Long tradingBotId,
                                                        @PathVariable("selectedCoinMarketsStrategyId") Long selectedCoinMarketsStrategyId,
                                                        @PathVariable("id") Long id) {

        selectedCoinService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
