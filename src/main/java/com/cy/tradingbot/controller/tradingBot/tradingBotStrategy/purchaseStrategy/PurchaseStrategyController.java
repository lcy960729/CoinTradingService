package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.purchaseStrategy;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.TradingBotStrategyController;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.PurchaseStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.request.RequestPurchaseStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PurchaseStrategyController<Entity extends PurchaseStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestPurchaseStrategyDTO, ResponseDTO extends ResponsePurchaseStrategyDTO >
        extends TradingBotStrategyController<Entity, PurchaseStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    public PurchaseStrategyController(TradingBotStrategyService<Entity, PurchaseStrategyRepository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
