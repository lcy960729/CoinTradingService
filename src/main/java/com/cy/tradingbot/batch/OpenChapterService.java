package com.cy.tradingbot.batch;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserver;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserverOffStrategy;
import com.cy.tradingbot.domain.log.service.LogService;
import com.cy.tradingbot.domain.coinTradingInfo.service.SchedulerOfUpdateCurrentPrice;
import com.cy.tradingbot.domain.coinTradingInfo.service.UpdateCurrentPriceServiceRunStrategy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OpenChapterService {
    private final LogService logService;
    private final TradingInfoRepository tradingInfoRepository;
    private final UpdateKrwBalanceAndTargetPriceAndMovingAverageListService updateKrwBalanceAndTargetPriceAndMovingAverageListService;
    private final OrderProcessorCompleteOfAllOrdersObserverOffStrategy orderProcessorCompleteOfAllOrdersObserverOffStrategy;
    private final OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver;
    private final UpdateCurrentPriceServiceRunStrategy currentPriceServiceRun;
    private final SchedulerOfUpdateCurrentPrice schedulerOfUpdateCurrentPrice;

    public OpenChapterService(UpdateKrwBalanceAndTargetPriceAndMovingAverageListService updateKrwBalanceAndTargetPriceAndMovingAverageListService, SchedulerOfUpdateCurrentPrice schedulerOfUpdateCurrentPrice, OrderProcessorCompleteOfAllOrdersObserverOffStrategy orderProcessorCompleteOfAllOrdersObserverOffStrategy, OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver, UpdateCurrentPriceServiceRunStrategy currentPriceServiceRun, LogService logService, TradingInfoRepository tradingInfoRepository) {
        this.updateKrwBalanceAndTargetPriceAndMovingAverageListService = updateKrwBalanceAndTargetPriceAndMovingAverageListService;
        this.schedulerOfUpdateCurrentPrice = schedulerOfUpdateCurrentPrice;
        this.orderProcessorCompleteOfAllOrdersObserverOffStrategy = orderProcessorCompleteOfAllOrdersObserverOffStrategy;
        this.orderProcessorCompleteOfAllOrdersObserver = orderProcessorCompleteOfAllOrdersObserver;
        this.currentPriceServiceRun = currentPriceServiceRun;
        this.logService = logService;
        this.tradingInfoRepository = tradingInfoRepository;
    }

    public void open() {
        orderProcessorCompleteOfAllOrdersObserver.setOrderProcessorCompleteOfAllOrdersObserverStrategy(orderProcessorCompleteOfAllOrdersObserverOffStrategy);

        Set<User> userSet = tradingInfoRepository.getUserSet();

        for (User user : userSet) {
            updateKrwBalanceAndTargetPriceAndMovingAverageListService.update(user);
            logService.write(user, "### 장 시작 ###");
        }

        schedulerOfUpdateCurrentPrice.setUpdateCurrentPriceService(currentPriceServiceRun);
    }
}
