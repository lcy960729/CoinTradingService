package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.selcetedCoin.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.selcetedCoin.SelectedCoin;
import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.selectedCoinMarketsStrategy.service.SelectedCoinMarketsStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinDTO;
import com.cy.tradingbot.exception.AccessDeniedException;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.SelectedCoinMapper;
import com.cy.tradingbot.repository.CoinRepository;
import com.cy.tradingbot.repository.SelectedCoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SelectedCoinService {

    private final SelectedCoinMarketsStrategyService selectedCoinMarketsStrategyService;
    private final SelectedCoinRepository selectedCoinRepository;
    private final CoinRepository coinRepository;
    private final SelectedCoinMapper selectedCoinMapper;

    public SelectedCoinService(SelectedCoinRepository selectedCoinRepository, CoinRepository coinRepository, SelectedCoinMapper selectedCoinMapper, SelectedCoinMarketsStrategyService selectedCoinMarketsStrategyService) {
        this.selectedCoinRepository = selectedCoinRepository;
        this.coinRepository = coinRepository;
        this.selectedCoinMapper = selectedCoinMapper;
        this.selectedCoinMarketsStrategyService = selectedCoinMarketsStrategyService;
    }

    public ResponseSelectedCoinDTO create(User user, long tradingBotId, long selectedCoinMarketsStrategyId, RequestSelectedCoinDTO requestSelectedCoinDTO) {
        SelectedCoin selectedCoin = new SelectedCoin();

        Coin coin = coinRepository.findById(requestSelectedCoinDTO.getCoinId()).orElseThrow(NotFoundEntityException::new);
        selectedCoin.setCoin(coin);

        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = selectedCoinMarketsStrategyService.getEntity(user, tradingBotId, selectedCoinMarketsStrategyId);
        selectedCoin.setSelectedCoinMarketsStrategy(selectedCoinMarketsStrategy);

        selectedCoin = selectedCoinRepository.save(selectedCoin);

        selectedCoinMarketsStrategy.addCoin(selectedCoin);
        coin.addCoin(selectedCoin);

        return selectedCoinMapper.toDTO(selectedCoin);
    }

    public ResponseSelectedCoinDTO get(User user, long tradingBotId, long selectedCoinMarketsStrategyId, long selectedCoinId) {
        SelectedCoin selectedCoin = getEntity(user, tradingBotId, selectedCoinMarketsStrategyId, selectedCoinId);

        return selectedCoinMapper.toDTO(selectedCoin);
    }

    private SelectedCoin getEntity(User user, long tradingBotId, long selectedCoinMarketsStrategyId, long selectedCoinId) {
        SelectedCoin selectedCoin = selectedCoinRepository.findById(selectedCoinId).orElseThrow(NotFoundEntityException::new);

        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = selectedCoin.getSelectedCoinMarketsStrategy();
        TradingBot tradingBot = selectedCoin.getSelectedCoinMarketsStrategy().getTradingBot();

        if (selectedCoinMarketsStrategy.getId() != selectedCoinMarketsStrategyId || tradingBot.getId() != tradingBotId || !tradingBot.getUser().equals(user))
            throw new AccessDeniedException();

        return selectedCoin;
    }


    public List<ResponseSelectedCoinDTO> getAll(User user, long tradingBotId, long selectedCoinMarketsStrategyId) {
        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = selectedCoinMarketsStrategyService.getEntity(user, tradingBotId, selectedCoinMarketsStrategyId);
        Set<SelectedCoin> selectedCoins = selectedCoinMarketsStrategy.getSelectedCoins();

        return selectedCoins.stream().map(selectedCoinMapper::toDTO).collect(Collectors.toList());
    }

    public void delete(User user, long tradingBotId, long selectedCoinMarketsStrategyId, long selectedCoinId) {
        SelectedCoin selectedCoin = getEntity(user, tradingBotId, selectedCoinMarketsStrategyId, selectedCoinId);

        selectedCoinRepository.delete(selectedCoin);
    }
}
