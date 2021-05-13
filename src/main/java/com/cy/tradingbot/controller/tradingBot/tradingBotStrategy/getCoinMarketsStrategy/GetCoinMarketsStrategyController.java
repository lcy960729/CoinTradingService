package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.TradingBotStrategyController;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.request.RequestGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class GetCoinMarketsStrategyController<Entity extends GetCoinMarketsStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestGetCoinMarketsStrategyDTO, ResponseDTO extends ResponseGetCoinMarketsStrategyDTO>
        extends TradingBotStrategyController<Entity, GetCoinMarketsStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    public GetCoinMarketsStrategyController(TradingBotStrategyService<Entity, GetCoinMarketsStrategyRepository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
