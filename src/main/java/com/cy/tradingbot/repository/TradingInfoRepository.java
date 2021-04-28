package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.candle.service.GetTickerService;
import com.cy.tradingbot.domain.market.Market;
import com.cy.tradingbot.domain.market.service.GetAllMarketsService;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfo;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoPurchasedStrategy;
import com.cy.tradingbot.domain.coinTradingInfo.CoinTradingInfoWaitingStrategy;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.CoinTradingInfoDTO;
import com.cy.tradingbot.domain.candle.service.GetCandlesService;
import com.cy.tradingbot.domain.wallet.service.GetWalletService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Set<String> markets = new HashSet<>();

    public TradingInfoRepository(CoinTradingInfoWaitingStrategy coinTradingInfoWaitingStrategy, GetCandlesService getCandlesService, GetWalletService getWalletService, CoinTradingInfoPurchasedStrategy coinTradingInfoPurchasedStrategy, GetAllMarketsService getAllMarketsService, GetTickerService getTickerService) {
        this.coinTradingInfoWaitingStrategy = coinTradingInfoWaitingStrategy;
        this.getCandlesService = getCandlesService;
        this.getWalletService = getWalletService;
        this.coinTradingInfoPurchasedStrategy = coinTradingInfoPurchasedStrategy;

        markets.addAll(getAllMarketsService.getAllMarkets().stream().map(market -> market.getMarket().substring(4)).collect(Collectors.toSet()));

        List<Candle> tickers = getTickerService.getTicker(markets);

        for (Candle ticker : tickers) {
            if (ticker.getTradePrice() < 100) {
                markets.remove(ticker.getCoinName().substring(4));
            }
        }
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

        if (user.getTradingSettings().getCoins().isEmpty()) {
            user.setCoinList(markets);
        } else {
            user.setCoinList(Arrays.stream(user.getTradingSettings().getCoins().trim().split(" ")).collect(Collectors.toSet()));
        }

        user.setNumOfPurchasedCoins(
                user.getCoinList().stream()
                        .mapToInt(coinName -> wallets.containsKey(coinName) ? 1 : 0)
                        .sum()
        );

        double krwBalance = wallets.get("KRW").getBalance();
        krwBalance = krwBalance / (double) (user.getNumOfCanPurchase());

        Set<CoinTradingInfo> coinTradingInfos = new HashSet<>();
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

            coinTradingInfos.add(coinTradingInfo);
        }

        addCoinTradingInfoAtTradingInfos(user, coinTradingInfos);
    }

    private void addCoinTradingInfoAtTradingInfos(User user, Set<CoinTradingInfo> coinTradingInfos) {
        tradingInfoFilteredUserMap.put(user, coinTradingInfos);

        for (CoinTradingInfo coinTradingInfo : coinTradingInfos) {
            final String coinName = coinTradingInfo.getCoinName();

            if (!tradingInfoFilteredCoinMap.containsKey(coinName))
                tradingInfoFilteredCoinMap.put(coinName, new HashSet<>());

            tradingInfoFilteredCoinMap.get(coinTradingInfo.getCoinName()).add(coinTradingInfo);

            tradingInfos.put(coinTradingInfo.getId(), coinTradingInfo);
        }
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
        Set<CoinTradingInfo> coinTradingInfos = getTradingInfosOfUser(user);

        return coinTradingInfos.stream()
                .sorted()
                .collect(Collectors.toList())
                .subList(0, Math.min(user.getTradingSettings().getNumOfCoinsForPurchase(), coinTradingInfos.size())).stream()
                .map(CoinTradingInfo::toDTO)
                .collect(Collectors.toList());
    }

}
