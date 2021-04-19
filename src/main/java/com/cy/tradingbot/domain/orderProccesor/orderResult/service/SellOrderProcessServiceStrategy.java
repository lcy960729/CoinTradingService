package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoStrategySetter;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.log.service.LogService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SellOrderProcessServiceStrategy implements OrderProcessService {

    private final CoinTradingInfoStrategySetter coinTradingInfoStrategySetter;

    private final TradingInfoRepository tradingInfoRepository;

    private final LogService logService;

    public SellOrderProcessServiceStrategy(CoinTradingInfoStrategySetter coinTradingInfoStrategySetter, TradingInfoRepository tradingInfoRepository, LogService logService) {
        this.coinTradingInfoStrategySetter = coinTradingInfoStrategySetter;
        this.tradingInfoRepository = tradingInfoRepository;
        this.logService = logService;
    }

    public void completed(OrderResult orderResult) {
        UUID coinTradingInfoId = orderResult.getOrderSheet().getCoinTradingInfoId();

        CoinTradingInfo coinTradingInfo = tradingInfoRepository.findById(coinTradingInfoId).orElseThrow(RuntimeException::new);

        coinTradingInfoStrategySetter.setWaiting(coinTradingInfo);

        coinTradingInfo.setWallet(null);

        logService.write(coinTradingInfo.getOrderer(), "[" + coinTradingInfo.getCoinName() + "] 매도 완료");
    }

}
