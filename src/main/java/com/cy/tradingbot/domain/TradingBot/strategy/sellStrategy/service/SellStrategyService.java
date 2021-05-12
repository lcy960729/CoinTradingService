package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service;

import com.cy.tradingbot.domain.TradingBot.service.GetTradingBotService;
import com.cy.tradingbot.domain.TradingBot.strategy.TradingBotStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.service.TradingBotStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBotStrategy.RequestTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestSellStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class SellStrategyService<Entity extends SellStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestSellStrategyDTO, ResponseDTO extends ResponseSellStrategyDTO>
        extends TradingBotStrategyService<Entity, SellStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    public SellStrategyService1(GetTradingBotService getTradingBotService, SellStrategyRepository repository, Mapper mapper) {
        super(getTradingBotService, repository, mapper);
    }
}
