package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyOrderProcessServiceStrategy implements OrderProcessService {
    private final TradingBotRepository tradingBotRepository;
    private final GetWalletService getWalletService;

    public BuyOrderProcessServiceStrategy( GetWalletService getWalletService, TradingBotRepository tradingBotRepository) {
        this.getWalletService = getWalletService;
        this.tradingBotRepository = tradingBotRepository;
    }

    @Override
    public void completed(OrderResult orderResult) {
        TradingBot tradingBot = tradingBotRepository.findById(orderResult.getOrderSheet().getTradingBotId()).orElseThrow(NotFoundEntityException::new);

        Wallet wallet = getWalletService.getCoinWallet(orderResult.getCoinName(), orderResult.getCredential());

        tradingBot.addPurchasedCoin(orderResult.getCoin(), wallet.getBalance());
    }
}
