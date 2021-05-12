package com.cy.tradingbot.domain.TradingBot.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.request.RequestTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDetailDTO;
import com.cy.tradingbot.exception.AccessDeniedException;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.TradingBotDetailMapper;
import com.cy.tradingbot.mapper.TradingBotMapper;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetTradingBotService {
    private final TradingBotRepository tradingBotRepository;

    public GetTradingBotService(TradingBotRepository tradingBotRepository) {
        this.tradingBotRepository = tradingBotRepository;
    }

    public TradingBot getEntity(User user, long tradingBotId) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        if (!tradingBot.getUser().equals(user))
            throw new AccessDeniedException();

        return tradingBot;
    }
}
