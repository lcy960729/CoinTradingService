package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoStrategySetter;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.log.service.LogService;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuyOrderProcessServiceStrategy implements OrderProcessService {
    private final LogService logService;

    private final GetWalletService getWalletService;

    private final CoinTradingInfoStrategySetter coinTradingInfoStrategySetter;

    private final TradingInfoRepository tradingInfoRepository;

    public BuyOrderProcessServiceStrategy(LogService logService, GetWalletService getWalletService, CoinTradingInfoStrategySetter coinTradingInfoStrategySetter, TradingInfoRepository tradingInfoRepository) {
        this.logService = logService;
        this.getWalletService = getWalletService;
        this.coinTradingInfoStrategySetter = coinTradingInfoStrategySetter;
        this.tradingInfoRepository = tradingInfoRepository;
    }

    @Override
    public void completed(OrderResult orderResult) {
        UUID coinTradingInfoId = orderResult.getOrderSheet().getCoinTradingInfoId();

        CoinTradingInfo coinTradingInfo = tradingInfoRepository.findById(coinTradingInfoId).orElseThrow(RuntimeException::new);

        coinTradingInfoStrategySetter.setPurchased(coinTradingInfo);

        Wallet wallet = getWalletService.getCoinWallet(coinTradingInfo.getCoinName(), coinTradingInfo.getOrderer().getCredential());
        coinTradingInfo.setWallet(wallet);

        logService.write(coinTradingInfo.getOrderer(), "[" + coinTradingInfo.getCoinName() + "] 매수 완료");
    }
}
