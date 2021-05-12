//package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.service;
//
//import com.cy.tradingbot.domain.TradingBot.TradingBot;
//import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
//import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service.SellStrategyService;
//import com.cy.tradingbot.domain.TradingBot.service.GetTradingBotService;
//import com.cy.tradingbot.domain.user.User;
//import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestCloseChapterStrategyDTO;
//import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
//import com.cy.tradingbot.exception.AccessDeniedException;
//import com.cy.tradingbot.exception.NotFoundEntityException;
//import com.cy.tradingbot.mapper.sellStrategy.CloseChapterStrategyMapper;
//import com.cy.tradingbot.repository.SellStrategyRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CloseChapterStrategyService extends SellStrategyService<RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> {
//    private final GetTradingBotService getTradingBotService;
//    private final CloseChapterStrategyMapper closeChapterStrategyMapper;
//
//    public CloseChapterStrategyService(SellStrategyRepository sellStrategyRepository, CloseChapterStrategyMapper closeChapterStrategyMapper, GetTradingBotService getTradingBotService) {
//        super(sellStrategyRepository);
//        this.closeChapterStrategyMapper = closeChapterStrategyMapper;
//        this.getTradingBotService = getTradingBotService;
//    }
//
//    public ResponseCloseChapterStrategyDTO create(User user, long tradingBotId, RequestCloseChapterStrategyDTO responseCloseChapterStrategyDTO) {
//        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);
//
//        CloseChapterStrategy closeChapterStrategy = new CloseChapterStrategy();
//        closeChapterStrategy.setTradingBot(tradingBot);
//
//        closeChapterStrategy = sellStrategyRepository.save(closeChapterStrategy);
//
//        return closeChapterStrategyMapper.toDTO(closeChapterStrategy);
//    }
//
//    @Override
//    public ResponseCloseChapterStrategyDTO update(User user, long tradingBotId, long sellStrategyId, RequestCloseChapterStrategyDTO responseCloseChapterStrategyDTO) {
//        return null;
//    }
//
//    @Override
//    public ResponseCloseChapterStrategyDTO get(User user, long tradingBotId, long sellStrategyId) {
//        return closeChapterStrategyMapper.toDTO(getEntity(user, tradingBotId, sellStrategyId));
//    }
//
//    private CloseChapterStrategy getEntity(User user, long tradingBotId, long sellStrategyId) {
//        CloseChapterStrategy closeChapterStrategy = (CloseChapterStrategy) sellStrategyRepository.findById(sellStrategyId).orElseThrow(NotFoundEntityException::new);
//        TradingBot tradingBot = closeChapterStrategy.getTradingBot();
//
//        if (tradingBot.getId() != tradingBotId || !tradingBot.getUser().equals(user)) {
//            throw new AccessDeniedException();
//        }
//
//        return closeChapterStrategy;
//    }
//}
