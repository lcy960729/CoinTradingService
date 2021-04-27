package com.cy.tradingbot.domain.user.service;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StopTradingService {
    private final TradingInfoRepository tradingInfoRepository;

    public StopTradingService(TradingInfoRepository tradingInfoRepository) {
        this.tradingInfoRepository = tradingInfoRepository;
    }

    public boolean stopTrading(User user) {
        if (!tradingInfoRepository.isExistUser(user))
            return false;

        Set<CoinTradingInfo> coinTradingInfos = tradingInfoRepository.getTradingInfosOfUser(user);

        for (CoinTradingInfo coinTradingInfo : coinTradingInfos) {
            if (!coinTradingInfo.stop())
                return false;
        }

        tradingInfoRepository.deleteTradingInfos(user);

        return true;
    }
}
