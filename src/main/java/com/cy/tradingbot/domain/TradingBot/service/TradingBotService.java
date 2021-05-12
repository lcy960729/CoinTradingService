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
import com.cy.tradingbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradingBotService {
    private final GetTradingBotService getTradingBotService;
    private final TradingBotRepository tradingBotRepository;
    private final TradingBotMapper tradingBotMapper;
    private final TradingBotDetailMapper tradingBotDetailMapper;

    public TradingBotService(TradingBotRepository tradingBotRepository, TradingBotMapper tradingBotMapper, TradingBotDetailMapper tradingBotDetailMapper, GetTradingBotService getTradingBotService) {
        this.tradingBotRepository = tradingBotRepository;
        this.tradingBotMapper = tradingBotMapper;
        this.tradingBotDetailMapper = tradingBotDetailMapper;
        this.getTradingBotService = getTradingBotService;
    }

    public ResponseTradingBotDetailDTO create(User user, RequestTradingBotDTO requestTradingBotDTO) {
        TradingBot tradingBot = new TradingBot();
        tradingBot.setUser(user);
        tradingBot.setName(requestTradingBotDTO.getName());
        tradingBot.setInvestmentRatio(requestTradingBotDTO.getInvestmentRatio());

        tradingBot = tradingBotRepository.save(tradingBot);

        return tradingBotDetailMapper.toDTO(tradingBot);
    }

    public ResponseTradingBotDetailDTO update(User user, long tradingBotId, RequestTradingBotDTO requestTradingBotDTO) {
        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);

        tradingBot.setName(requestTradingBotDTO.getName());
        tradingBot.setInvestmentRatio(requestTradingBotDTO.getInvestmentRatio());

        tradingBot = tradingBotRepository.save(tradingBot);

        return tradingBotDetailMapper.toDTO(tradingBot);
    }

    public ResponseTradingBotDetailDTO get(User user, long tradingBotId) {
        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);
        return tradingBotDetailMapper.toDTO(tradingBot);
    }

    public List<ResponseTradingBotDTO> getAll(User user) {
        List<TradingBot> tradingBots = tradingBotRepository.findAllByUserId(user.getId()).orElseThrow(NotFoundEntityException::new);

        return tradingBots.stream()
                .map(tradingBotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(User user, Long tradingBotId) {
        TradingBot tradingBot = getTradingBotService.getEntity(user, tradingBotId);

        tradingBotRepository.delete(tradingBot);
    }
}
