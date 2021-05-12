package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.service;

import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.purchaseStrategy.PurchaseStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestPurchaseStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponsePurchaseStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.PurchaseStrategyRepository;

public abstract class PurchaseStrategyService<Entity extends PurchaseStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestPurchaseStrategyDTO, ResponseDTO extends ResponsePurchaseStrategyDTO >
        extends TradingBotStrategyService<Entity, PurchaseStrategyRepository, Mapper, RequestDTO, ResponseDTO> {


    protected PurchaseStrategyService(TradingBotService tradingBotService, PurchaseStrategyRepository repository, Mapper mapper) {
        super(tradingBotService, repository, mapper);
    }
}