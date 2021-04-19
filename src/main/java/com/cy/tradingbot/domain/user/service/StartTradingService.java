package com.cy.tradingbot.domain.user.service;

import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class StartTradingService {

    private final TradingInfoRepository tradingInfoRepository;

    public StartTradingService(TradingInfoRepository tradingInfoRepository) {
        this.tradingInfoRepository = tradingInfoRepository;
    }

    public void createTradingInfos(User user) {
        if (tradingInfoRepository.isExistUser(user))
            return;

        tradingInfoRepository.createTradingInfos(user);
    }
}
