package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.service;

import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.request.RequestSellStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;

public abstract class SellStrategyService<Entity extends SellStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestSellStrategyDTO, ResponseDTO extends ResponseSellStrategyDTO>
        extends TradingBotStrategyService<Entity, SellStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    public SellStrategyService(TradingBotService tradingBotService, SellStrategyRepository repository, Mapper mapper) {
        super(tradingBotService, repository, mapper);
    }
}
