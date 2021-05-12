package com.cy.tradingbot.dto.tradingBot.response;

import com.cy.tradingbot.controller.TradingBotController;
import com.cy.tradingbot.controller.getCoinMarketsStrategy.SelectedCoinMarketsStrategyController;
import com.cy.tradingbot.controller.getCoinMarketsStrategy.TopChangeRateCoinMarketsStrategyController;
import com.cy.tradingbot.controller.investmentCalculator.*;
import com.cy.tradingbot.controller.purchaseStrategy.VolatilityBreakoutStrategyController;
import com.cy.tradingbot.controller.sellStrategy.CloseChapterStrategyController;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class ResponseTradingBotDetailDTO extends RepresentationModel<ResponseTradingBotDetailDTO> {
    private Long id;
    private String name;
    private Double investmentRatio;
    private Boolean isRunning;

    private ResponseGetCoinMarketsStrategyDTO getCoinMarketsStrategy;
    private List<ResponsePurchaseStrategyDTO> purchaseStrategy = new ArrayList<>();
    private List<ResponseSellStrategyDTO> sellStrategy = new ArrayList<>();
    private ResponseInvestmentCalculatorDTO investmentCalculator;

    public void makeLinks() {
        add(linkTo(methodOn(TradingBotController.class).get(null, id)).withSelfRel());

        add(linkTo(methodOn(TradingBotController.class).update(null, id, null)).withRel("update-tradingBot"));
        add(linkTo(methodOn(TradingBotController.class).delete(null, id)).withRel("delete-tradingBot"));

        add(linkTo(methodOn(SelectedCoinMarketsStrategyController.class).create(null, id, null)).withRel("create-selectedCoinMarketsStrategy"));
        add(linkTo(methodOn(TopChangeRateCoinMarketsStrategyController.class).create(null, id, null)).withRel("create-topChangeRateCoinMarketsStrategy"));

        add(linkTo(methodOn(VolatilityBreakoutStrategyController.class).create(null, id, null)).withRel("create-volatilityBreakoutStrategy"));

        add(linkTo(methodOn(CloseChapterStrategyController.class).create(null, id, null)).withRel("create-closeChapterStrategy"));

        add(linkTo(methodOn(CoinsRatioCalculatorController.class).create(null, id, null)).withRel("create-coinsRatioCalculator"));
        add(linkTo(methodOn(FixedRatioCalculatorController.class).create(null, id, null)).withRel("create-fixedRatioCalculator"));
        add(linkTo(methodOn(FixedAmountCalculatorController.class).create(null, id, null)).withRel("create-fixedAmountCalculator"));
        add(linkTo(methodOn(YesterdayVolatilityCalculatorController.class).create(null, id, null)).withRel("create-yesterdayVolatilityCalculator"));
        add(linkTo(methodOn(ScoreOfMovingAverageCalculatorController.class).create(null, id, null)).withRel("create-scoreOfMovingAverageCalculator"));
    }
}
