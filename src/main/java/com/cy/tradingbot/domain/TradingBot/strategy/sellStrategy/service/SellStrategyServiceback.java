//package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service;
//
//import com.cy.tradingbot.domain.user.User;
//import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestSellStrategyDTO;
//import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseSellStrategyDTO;
//import com.cy.tradingbot.repository.SellStrategyRepository;
//
//public abstract class SellStrategyService<K extends RequestSellStrategyDTO, V extends ResponseSellStrategyDTO> {
//
//    protected SellStrategyRepository sellStrategyRepository;
//
//    public SellStrategyService(SellStrategyRepository sellStrategyRepository) {
//        this.sellStrategyRepository = sellStrategyRepository;
//    }
//
//    public abstract V create(User user, long tradingBotId, K requestSellStrategyDTO);
//
//    public abstract V update(User user, long tradingBotId, long sellStrategyId, K requestSellStrategyDTO);
//
//    public void delete(User user, long tradingBotId, long sellStrategyId) {
//        sellStrategyRepository.deleteById(sellStrategyId);
//    }
//
//    public abstract V get(User user, long tradingBotId, long sellStrategyId);
//}
