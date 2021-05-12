package com.cy.tradingbot.domain.tradingBot.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.request.RequestTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDTO;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDetailDTO;
import com.cy.tradingbot.exception.AccessDeniedException;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.TradingBotDetailMapper;
import com.cy.tradingbot.mapper.TradingBotMapper;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradingBotService {
    private final TradingBotRepository tradingBotRepository;
    private final TradingBotMapper tradingBotMapper;
    private final TradingBotDetailMapper tradingBotDetailMapper;

    public TradingBotService(TradingBotRepository tradingBotRepository, TradingBotMapper tradingBotMapper, TradingBotDetailMapper tradingBotDetailMapper ) {
        this.tradingBotRepository = tradingBotRepository;
        this.tradingBotMapper = tradingBotMapper;
        this.tradingBotDetailMapper = tradingBotDetailMapper;
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
        TradingBot tradingBot = getEntity(user, tradingBotId);

        tradingBot.setName(requestTradingBotDTO.getName());
        tradingBot.setInvestmentRatio(requestTradingBotDTO.getInvestmentRatio());

        tradingBot = tradingBotRepository.save(tradingBot);

        return tradingBotDetailMapper.toDTO(tradingBot);
    }

    public ResponseTradingBotDetailDTO get(User user, long tradingBotId) {
        TradingBot tradingBot = getEntity(user, tradingBotId);
        return tradingBotDetailMapper.toDTO(tradingBot);
    }

    public List<ResponseTradingBotDTO> getAll(User user) {
        List<TradingBot> tradingBots = tradingBotRepository.findAllByUserId(user.getId()).orElseThrow(NotFoundEntityException::new);

        return tradingBots.stream()
                .map(tradingBotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(User user, Long tradingBotId) {
        TradingBot tradingBot = getEntity(user, tradingBotId);

        tradingBotRepository.delete(tradingBot);
    }

    public TradingBot getEntity(User user, long tradingBotId) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        if (!tradingBot.getUser().equals(user))
            throw new AccessDeniedException();

        return tradingBot;
    }
}
