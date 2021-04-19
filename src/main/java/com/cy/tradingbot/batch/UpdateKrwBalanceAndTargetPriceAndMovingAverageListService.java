package com.cy.tradingbot.batch;

import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.TradingInfoRepository;
import com.cy.tradingbot.domain.candle.service.GetCandlesService;
import com.cy.tradingbot.domain.record.service.RecordService;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UpdateKrwBalanceAndTargetPriceAndMovingAverageListService {
    private final TradingInfoRepository tradingInfoRepository;
    private final GetWalletService getWalletService;
    private final GetCandlesService getCandlesService;
    private final RecordService recordService;

    public UpdateKrwBalanceAndTargetPriceAndMovingAverageListService(TradingInfoRepository tradingInfoRepository, GetWalletService getWalletService, GetCandlesService getCandlesService, RecordService recordService) {
        this.tradingInfoRepository = tradingInfoRepository;
        this.getWalletService = getWalletService;
        this.getCandlesService = getCandlesService;
        this.recordService = recordService;
    }

    public void update(User user) {
        Set<CoinTradingInfo> coinTradingInfos = tradingInfoRepository.getTradingInfosOfUser(user);

        Wallet krwWallet = getWalletService.getKrwWallet(user.getCredential());

        recordService.write(user, krwWallet.getBalance());

        double krwBalance = krwWallet.getBalance() / user.getCoinList().size();

        coinTradingInfos.forEach(coinTradingInfo -> updateTargetPriceAndMovingAverage(coinTradingInfo, krwBalance));
    }

    private void updateTargetPriceAndMovingAverage(CoinTradingInfo coinTradingInfo, double krwBalance) {
        List<Candle> candles = getCandlesService.getCandles(coinTradingInfo.getCoinName(), coinTradingInfo.getOrderer().getTradingSettings().getMaxOfCandles());

        coinTradingInfo.setCandles(candles);
        coinTradingInfo.setKrwBalance(krwBalance);
        coinTradingInfo.calcTargetPrice();
        coinTradingInfo.calcMovingAverage();
    }
}
