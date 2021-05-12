//package com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.volatilityBreakoutStrategy;
//
//import com.cy.tradingbot.domain.TradingBot.TradingBot;
//import com.cy.tradingbot.domain.TradingBot.strategy.purchaseStrategy.service.PurchaseStrategyService;
//import com.cy.tradingbot.domain.TradingBot.service.GetTradingBotService;
//import com.cy.tradingbot.domain.user.User;
//import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.request.RequestVolatilityBreakoutStrategyDTO;
//import com.cy.tradingbot.dto.tradingBotStrategy.purchaseStrategy.response.ResponseVolatilityBreakoutStrategyDTO;
//import com.cy.tradingbot.exception.AccessDeniedException;
//import com.cy.tradingbot.exception.NotFoundEntityException;
//import com.cy.tradingbot.mapper.purchaseStrategy.VolatilityBreakoutStrategyMapper;
//import com.cy.tradingbot.repository.PurchaseStrategyRepository;
//import com.cy.tradingbot.repository.TradingBotRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VolatilityBreakoutDailyStrategyService extends PurchaseStrategyService<RequestVolatilityBreakoutStrategyDTO, ResponseVolatilityBreakoutStrategyDTO> {
//    private final GetTradingBotService getTradingBotService;
//    private final VolatilityBreakoutStrategyMapper volatilityBreakoutStrategyMapper;
//
//    protected VolatilityBreakoutDailyStrategyService(PurchaseStrategyRepository purchaseStrategyRepository, TradingBotRepository tradingBotRepository, VolatilityBreakoutStrategyMapper volatilityBreakoutStrategyMapper, GetTradingBotService getTradingBotService) {
//        super(purchaseStrategyRepository);
//        this.volatilityBreakoutStrategyMapper = volatilityBreakoutStrategyMapper;
//        this.getTradingBotService = getTradingBotService;
//    }
//
//    public ResponseVolatilityBreakoutStrategyDTO create(User user, long tradingBotId, RequestVolatilityBreakoutStrategyDTO responseVolatilityBreakoutStrategyDTO) {
//        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);
//
//        VolatilityBreakoutStrategy volatilityBreakoutStrategy = new VolatilityBreakoutStrategy();
//        volatilityBreakoutStrategy.setTradingBot(tradingBot);
//        volatilityBreakoutStrategy.setMaxOfCandles(responseVolatilityBreakoutStrategyDTO.getMaxOfCandles());
//
//        volatilityBreakoutStrategy = purchaseStrategyRepository.save(volatilityBreakoutStrategy);
//
//        return volatilityBreakoutStrategyMapper.toDTO(volatilityBreakoutStrategy);
//    }
//
//    @Override
//    public ResponseVolatilityBreakoutStrategyDTO update(User user, long tradingBotId, long purchaseStrategyId, RequestVolatilityBreakoutStrategyDTO requestResponseVolatilityBreakoutStrategyDTO) {
//        VolatilityBreakoutStrategy volatilityBreakoutStrategy = (VolatilityBreakoutStrategy) purchaseStrategyRepository.findById(purchaseStrategyId).orElseThrow(NotFoundEntityException::new);
//        volatilityBreakoutStrategy.setMaxOfCandles(requestResponseVolatilityBreakoutStrategyDTO.getMaxOfCandles());
//
//        volatilityBreakoutStrategy = purchaseStrategyRepository.save(volatilityBreakoutStrategy);
//
//        return volatilityBreakoutStrategyMapper.toDTO(volatilityBreakoutStrategy);
//    }
//
//    @Override
//    public ResponseVolatilityBreakoutStrategyDTO get(User user, long tradingBotId, long purchaseStrategyId) {
//        VolatilityBreakoutStrategy volatilityBreakoutStrategy = (VolatilityBreakoutStrategy) purchaseStrategyRepository.findById(purchaseStrategyId).orElseThrow(NotFoundEntityException::new);
//
//        TradingBot tradingBot = volatilityBreakoutStrategy.getTradingBot();
//
//        if (tradingBot.getId() != tradingBotId || tradingBot.getUser().equals(user))
//            throw new AccessDeniedException();
//
//        return volatilityBreakoutStrategyMapper.toDTO(volatilityBreakoutStrategy);
//    }
//
//    public ResponseVolatilityBreakoutStrategyDTO getEntity(User user, long tradingBotId, long purchaseStrategyId) {
//        VolatilityBreakoutStrategy volatilityBreakoutStrategy = (VolatilityBreakoutStrategy) purchaseStrategyRepository.findById(purchaseStrategyId).orElseThrow(NotFoundEntityException::new);
//
//        TradingBot tradingBot = volatilityBreakoutStrategy.getTradingBot();
//
//        if (tradingBot.getId() != tradingBotId || tradingBot.getUser().equals(user))
//            throw new AccessDeniedException();
//
//        return volatilityBreakoutStrategyMapper.toDTO(volatilityBreakoutStrategy);
//    }
//}
