package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.GetCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.RequestTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.ResponseTradingBotStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.request.RequestGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseGetCoinMarketsStrategyDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;

public abstract class GetCoinMarketsStrategyService<Entity extends GetCoinMarketsStrategy, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestGetCoinMarketsStrategyDTO & RequestTradingBotStrategyDTO, ResponseDTO extends ResponseGetCoinMarketsStrategyDTO & ResponseTradingBotStrategyDTO>
        extends TradingBotStrategyService<Entity, GetCoinMarketsStrategyRepository, Mapper, RequestDTO, ResponseDTO> {

    protected GetCoinMarketsStrategyService(TradingBotService tradingBotService, GetCoinMarketsStrategyRepository repository, Mapper mapper) {
        super(tradingBotService, repository, mapper);
    }
}
