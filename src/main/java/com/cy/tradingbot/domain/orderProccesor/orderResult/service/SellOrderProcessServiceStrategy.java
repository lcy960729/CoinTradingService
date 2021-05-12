package com.cy.tradingbot.domain.orderProccesor.orderResult.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class SellOrderProcessServiceStrategy implements OrderProcessService {
    private final TradingBotRepository tradingBotRepository;

    public SellOrderProcessServiceStrategy(TradingBotRepository tradingBotRepository) {
        this.tradingBotRepository = tradingBotRepository;
    }

    public void completed(OrderResult orderResult) {
        TradingBot tradingBot = tradingBotRepository.findById(orderResult.getOrderSheet().getTradingBotId()).orElseThrow(NotFoundEntityException::new);
        tradingBot.removePurchasedCoin(orderResult.getCoin());

        double krwBalance = tradingBot.getKrwBalance();

        krwBalance += orderResult.getPrice() * orderResult.getVolume();
        tradingBot.setKrwBalance(krwBalance);
    }
}
