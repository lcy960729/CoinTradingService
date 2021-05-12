package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.closeChapterStartegy.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.sellStrategy.service.SellStrategyService;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.request.RequestCloseChapterStrategyDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.sellStrategy.response.ResponseCloseChapterStrategyDTO;
import com.cy.tradingbot.mapper.sellStrategy.CloseChapterStrategyMapper;
import com.cy.tradingbot.repository.SellStrategyRepository;
import org.springframework.stereotype.Service;

@Service
public class CloseChapterStrategyService extends SellStrategyService<CloseChapterStrategy, CloseChapterStrategyMapper, RequestCloseChapterStrategyDTO, ResponseCloseChapterStrategyDTO> {

    protected CloseChapterStrategyService(TradingBotService tradingBotService, SellStrategyRepository repository, CloseChapterStrategyMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected CloseChapterStrategy createEntity(TradingBot tradingBot, RequestCloseChapterStrategyDTO requestCloseChapterStrategyDTO) {
        CloseChapterStrategy closeChapterStrategy = new CloseChapterStrategy();
        closeChapterStrategy.setTradingBot(tradingBot);

        updateEntity(closeChapterStrategy, requestCloseChapterStrategyDTO);

        return closeChapterStrategy;
    }

    @Override
    protected void updateEntity(CloseChapterStrategy closeChapterStrategy, RequestCloseChapterStrategyDTO requestCloseChapterStrategyDTO) {

    }
}
