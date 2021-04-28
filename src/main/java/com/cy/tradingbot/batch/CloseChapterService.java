package com.cy.tradingbot.batch;

import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoPurchasedStrategy;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoStrategySetter;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.coinTradingInfo.service.OrderSheetPublisher;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserver;
import com.cy.tradingbot.domain.orderProccesor.service.OrderProcessorCompleteOfAllOrdersObserverOnStrategy;
import com.cy.tradingbot.domain.log.service.LogService;
import com.cy.tradingbot.domain.coinTradingInfo.service.SchedulerOfUpdateCurrentPrice;
import com.cy.tradingbot.domain.coinTradingInfo.service.UpdateCurrentPriceServiceStopStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.Map;
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
    private final GetWalletService getWalletService;

    private final CoinTradingInfoStrategySetter coinTradingInfoStrategySetter;

    public CloseChapterService(TradingInfoRepository tradingInfoRepository, SchedulerOfUpdateCurrentPrice schedulerOfUpdateCurrentPrice, OrderProcessorCompleteOfAllOrdersObserverOnStrategy orderProcessorCompleteOfAllOrdersObserverOnStrategy, OrderProcessorCompleteOfAllOrdersObserver orderProcessorCompleteOfAllOrdersObserver, OrderSheetPublisher orderSheetPublisher, UpdateCurrentPriceServiceStopStrategy updateCurrentPriceServiceStopStrategy, LogService logService, GetWalletService getWalletService, CoinTradingInfoStrategySetter coinTradingInfoStrategySetter) {
        this.tradingInfoRepository = tradingInfoRepository;
        this.schedulerOfUpdateCurrentPrice = schedulerOfUpdateCurrentPrice;
        this.orderProcessorCompleteOfAllOrdersObserverOnStrategy = orderProcessorCompleteOfAllOrdersObserverOnStrategy;
        this.orderProcessorCompleteOfAllOrdersObserver = orderProcessorCompleteOfAllOrdersObserver;
        this.orderSheetPublisher = orderSheetPublisher;
        this.updateCurrentPriceServiceStopStrategy = updateCurrentPriceServiceStopStrategy;
        this.logService = logService;
        this.getWalletService = getWalletService;
        this.coinTradingInfoStrategySetter = coinTradingInfoStrategySetter;
    }

    @Scheduled(cron = "10 0 9 * * *", zone = "Asia/Seoul")
    public void close() {
        schedulerOfUpdateCurrentPrice.setUpdateCurrentPriceService(updateCurrentPriceServiceStopStrategy);

        Set<User> users = tradingInfoRepository.getUserSet();

        for (User user : users) {
            logService.write(user, "### 장마감 ###");

            Map<String, Wallet> wallets = getWalletService.getWalletHashTable(user.getCredential());

            Set<CoinTradingInfo> coinTradingInfos = tradingInfoRepository.getTradingInfosOfUser(user);

            for (CoinTradingInfo coinTradingInfo : coinTradingInfos) {
                String coinName = coinTradingInfo.getCoinName();

                // 해당 코인의 지갑이 있을때만 지갑을 설정하고 파낟.
                if (wallets.containsKey(coinName)){
                    coinTradingInfo.setWallet(wallets.get(coinName));
                    sellCoin(coinTradingInfo);
                }else{
                    // 없으면 매도 된것으로 처리
                    coinTradingInfoStrategySetter.setWaiting(coinTradingInfo);
                    coinTradingInfo.setWallet(null);
                    coinTradingInfo.getOrderer().minusNumOfPurchasedCoins();

                    logService.write(coinTradingInfo.getOrderer(), "[" + coinTradingInfo.getCoinName() + "] 매도 완료");
                }
            }
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
