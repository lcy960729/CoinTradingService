package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.service.GetTradingBotService;
import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.service.SellStrategyService1;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestCloseChapterStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
import com.cy.tradingbot.mapper.sellStrategy.CloseChapterStrategyMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class CloseChapterStrategyService1 extends SellStrategyService1<CloseChapterStrategy, CloseChapterStrategyMapper, RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> {

    protected CloseChapterStrategyService1(GetTradingBotService getTradingBotService, SellStrategyRepository repository, CloseChapterStrategyMapper mapper) {
        super(getTradingBotService, repository, mapper);
    }

    @Override
    protected CloseChapterStrategy createEntity(TradingBot tradingBot, RequestCloseChapterStrategyDTO requestCloseChapterStrategyDTO) {
        CloseChapterStrategy closeChapterStrategy = new CloseChapterStrategy();
        closeChapterStrategy.setTradingBot(tradingBot);

        return closeChapterStrategy;
    }

    @Override
    protected void updateEntity(CloseChapterStrategy closeChapterStrategy, RequestCloseChapterStrategyDTO requestCloseChapterStrategyDTO) {

    }
}
