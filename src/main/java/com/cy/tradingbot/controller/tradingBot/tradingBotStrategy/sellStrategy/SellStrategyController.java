package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.sellStrategy;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.TradingBotStrategyController;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.SellStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.request.RequestSellStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class SellStrategyController<Entity extends SellStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestSellStrategyDTO, ResponseDTO extends ResponseSellStrategyDTO>
        extends TradingBotStrategyController<Entity, SellStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    public SellStrategyController(TradingBotStrategyService<Entity, SellStrategyRepository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
