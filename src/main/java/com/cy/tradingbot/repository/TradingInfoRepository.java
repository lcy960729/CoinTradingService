package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoPurchasedStrategy;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoWaitingStrategy;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.CoinTradingInfoDTO;
import com.cy.tradingbot.domain.candle.service.GetCandlesService;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TradingInfoRepository {
    private final Map<User, Set<CoinTradingInfo>> tradingInfoFilteredUserMap = new HashMap<>();
    private final Map<String, Set<CoinTradingInfo>> tradingInfoFilteredCoinMap = new HashMap<>();
    private final Map<UUID, CoinTradingInfo> tradingInfos = new HashMap<>();

    private final CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy;
    private final CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy;
    private final GetCandlesService getCandlesService;
    private final GetWalletService getWalletService;

    public TradingInfoRepository(CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy, GetCandlesService getCandlesService, GetWalletService getWalletService, CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy) {
        this.coinTradingInfoWaitingStrategy = coinTradingInfoWaitingStrategy;
        this.getCandlesService = getCandlesService;
        this.getWalletService = getWalletService;
        this.coinTradingInfoPurchasedStrategy = coinTradingInfoPurchasedStrategy;
    }

    public Set<CoinTradingInfo> getTradingInfosOfUser(User user) {
        if (!tradingInfoFilteredUserMap.containsKey(user)) return null;

        return tradingInfoFilteredUserMap.get(user);
    }

    public Set<CoinTradingInfo> getTradingInfosOfCoin(String coinName) {
        if (!tradingInfoFilteredCoinMap.containsKey(coinName)) return null;

        return tradingInfoFilteredCoinMap.get(coinName);
    }

    public void createTradingInfos(User user) {
        Map<String, Wallet> wallets = getWalletService.getWalletHashTable(user.getCredential());

        int numOfPurchasedCoin = user.getCoinList().stream()
                .mapToInt(coinName -> wallets.containsKey(coinName) ? 1 : 0)
                .sum();

        double krwBalance = wallets.get("KRW").getBalance();
        krwBalance = krwBalance / (double) (user.getCoinList().size() - numOfPurchasedCoin);

        for (String coinName : user.getCoinList()) {
            CoinTradingInfo coinTradingInfo = CoinTradingInfo.builder()
                    .coinName(coinName)
                    .orderer(user)
                    .build();

            List<Candle> candles = getCandlesService.getCandles(coinName, user.getTradingSettings().getMaxOfCandles());

            coinTradingInfo.setCandles(candles);
            coinTradingInfo.calcMovingAverage();
            coinTradingInfo.calcTargetPrice();

            if (wallets.containsKey(coinName)) {
                coinTradingInfo.setWallet(wallets.get(coinName));
                coinTradingInfo.setCoinTradingInfoStrategy(coinTradingInfoPurchasedStrategy);
            } else {
                coinTradingInfo.setWallet(null);
                coinTradingInfo.setKrwBalance(krwBalance);
                coinTradingInfo.setCoinTradingInfoStrategy(coinTradingInfoWaitingStrategy);
            }

            addCoinTradingInfoAtTradingInfos(coinTradingInfo);
        }
    }

    private void addCoinTradingInfoAtTradingInfos(CoinTradingInfo coinTradingInfo) {
        final User user = coinTradingInfo.getOrderer();

        if (!tradingInfoFilteredUserMap.containsKey(user)) {
            tradingInfoFilteredUserMap.put(user, new HashSet<>());
        }
        tradingInfoFilteredUserMap.get(user).add(coinTradingInfo);

        final String coinName = coinTradingInfo.getCoinName();

        if (!tradingInfoFilteredCoinMap.containsKey(coinName))
            tradingInfoFilteredCoinMap.put(coinName, new HashSet<>());

        tradingInfoFilteredCoinMap.get(coinTradingInfo.getCoinName()).add(coinTradingInfo);

        tradingInfos.put(coinTradingInfo.getId(), coinTradingInfo);
    }

    public Set<User> getUserSet() {
        return tradingInfoFilteredUserMap.keySet();
    }

    public Set<String> getCoinSet() {
        return tradingInfoFilteredCoinMap.keySet();
    }

    public void deleteTradingInfos(User user) {
        Set<CoinTradingInfo> coinTradingInfos = getTradingInfosOfUser(user);

        coinTradingInfos.forEach(coinTradingInfo -> {
            final String coinName = coinTradingInfo.getCoinName();
            Set<CoinTradingInfo> coinTradingInfosFilteredCoin = tradingInfoFilteredCoinMap.get(coinName);

            coinTradingInfosFilteredCoin.remove(coinTradingInfo);

            if (coinTradingInfosFilteredCoin.isEmpty())
                tradingInfoFilteredCoinMap.remove(coinName);

            tradingInfos.remove(coinTradingInfo.getId());
        });

        tradingInfoFilteredUserMap.remove(user);
    }

    public Optional<CoinTradingInfo> findById(UUID uuid) {
        return Optional.of(tradingInfos.get(uuid));
    }


    public boolean isExistUser(User user) {
        return tradingInfoFilteredUserMap.containsKey(user);
    }

    public List<CoinTradingInfoDTO> getCoinTradingInfoDTOList(User user) {
        return getTradingInfosOfUser(user).stream()
                .map(CoinTradingInfo::toDTO)
                .collect(Collectors.toList());
    }
}
