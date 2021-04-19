package com.cy.tradingbot.batch;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoPurchasedStrategy;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.coinTradingInfo.service.OrderSheetPublisher;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserver;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserverOnStrategy;
import com.cy.tradingbot.domain.log.service.LogService;
import com.cy.tradingbot.domain.coinTradingInfo.service.SchedulerOfUpdateCurrentPrice;
import com.cy.tradingbot.domain.coinTradingInfo.service.UpdateCurrentPriceServiceStopStrategy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CloseChapterService {
    private final LogService logService;
    private final SchedulerOfUpdateCurrentPrice schedulerOfUpdateCurrentPrice;
    private final OrderProcessorCompleteOfAllOrdersObserverOnStrategy orderProcessorCompleteOfAllOrdersObserverOnStrategy;
    private final OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver;
    private final TradingInfoRepository tradingInfoRepository;
    private final OrderSheetPublisher orderSheetPublisher;
    private final UpdateCurrentPriceServiceStopStrategy updateCurrentPriceServiceStopStrategy;

    public CloseChapterService(TradingInfoRepository tradingInfoRepository, SchedulerOfUpdateCurrentPrice schedulerOfUpdateCurrentPrice, OrderProcessorCompleteOfAllOrdersObserverOnStrategy orderProcessorCompleteOfAllOrdersObserverOnStrategy, OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver, OrderSheetPublisher orderSheetPublisher, UpdateCurrentPriceServiceStopStrategy updateCurrentPriceServiceStopStrategy, LogService logService) {
        this.tradingInfoRepository = tradingInfoRepository;
        this.schedulerOfUpdateCurrentPrice = schedulerOfUpdateCurrentPrice;
        this.orderProcessorCompleteOfAllOrdersObserverOnStrategy = orderProcessorCompleteOfAllOrdersObserverOnStrategy;
        this.orderProcessorCompleteOfAllOrdersObserver = orderProcessorCompleteOfAllOrdersObserver;
        this.orderSheetPublisher = orderSheetPublisher;
        this.updateCurrentPriceServiceStopStrategy = updateCurrentPriceServiceStopStrategy;
        this.logService = logService;
    }

    @Scheduled(cron = "10 0 9 * * *", zone = "Asia/Seoul")
    public void close() {
        schedulerOfUpdateCurrentPrice.setUpdateCurrentPriceService(updateCurrentPriceServiceStopStrategy);

        Set<User> users = tradingInfoRepository.getUserSet();

        for (User user : users) {
            logService.write(user, "### 장마감 ###");
            Set<CoinTradingInfo> coinTradingInfos = tradingInfoRepository.getTradingInfosOfUser(user);
            coinTradingInfos.forEach(this::sellCoin);
        }

        orderProcessorCompleteOfAllOrdersObserver.setOrderProcessorCompleteOfAllOrdersObserverStrategy(orderProcessorCompleteOfAllOrdersObserverOnStrategy);
    }


    private boolean isPurchasedCoin(CoinTradingInfo coinTradingInfo) {
        return (coinTradingInfo.getCoinTradingInfoStrategy() instanceof CoinTradingInfoPurchasedStrategy);
    }

    private void sellCoin(CoinTradingInfo coinTradingInfo) {
        if (!isPurchasedCoin(coinTradingInfo))
            return;

        orderSheetPublisher.publish(coinTradingInfo.createSellOrderSheet());
    }
}
