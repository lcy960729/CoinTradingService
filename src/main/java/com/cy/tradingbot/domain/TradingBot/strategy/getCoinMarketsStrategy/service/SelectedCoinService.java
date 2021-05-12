package com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.service;

import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoinMarketsStrategy;
import com.cy.tradingbot.domain.TradingBot.strategy.getCoinMarketsStrategy.SelectedCoin;
import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.request.RequestSelectedCoinDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.getCoinMarketsStrategy.response.ResponseSelectedCoinDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.SelectedCoinMapper;
import com.cy.tradingbot.repository.CoinRepository;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import com.cy.tradingbot.repository.SelectedCoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectedCoinService {
    private final GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository;
    private final SelectedCoinRepository selectedCoinRepository;
    private final CoinRepository coinRepository;
    private final SelectedCoinMapper selectedCoinMapper;

    public SelectedCoinService(GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository, SelectedCoinRepository selectedCoinRepository, CoinRepository coinRepository, SelectedCoinMapper selectedCoinMapper) {
        this.getCoinMarketsStrategyRepository = getCoinMarketsStrategyRepository;
        this.selectedCoinRepository = selectedCoinRepository;
        this.coinRepository = coinRepository;
        this.selectedCoinMapper = selectedCoinMapper;
    }

    public ResponseSelectedCoinDTO create(long getCoinMarketsStrategyId, RequestSelectedCoinDTO requestSelectedCoinDTO) {
        SelectedCoin selectedCoin = new SelectedCoin();

        Coin coin = coinRepository.findById(requestSelectedCoinDTO.getCoinId()).orElseThrow(NotFoundEntityException::new);
        selectedCoin.setCoin(coin);

        SelectedCoinMarketsStrategy selectedCoinMarketsStrategy = (SelectedCoinMarketsStrategy) getCoinMarketsStrategyRepository.findById(getCoinMarketsStrategyId).orElseThrow(NotFoundEntityException::new);
        selectedCoin.setSelectedCoinMarketsStrategy(selectedCoinMarketsStrategy);

        selectedCoin = selectedCoinRepository.save(selectedCoin);

        selectedCoinMarketsStrategy.addCoin(selectedCoin);
        coin.addCoin(selectedCoin);

        return selectedCoinMapper.toDTO(selectedCoin);
    }

    public ResponseSelectedCoinDTO get(long selectedCoinId) {
        SelectedCoin selectedCoin = selectedCoinRepository.findById(selectedCoinId).orElseThrow(NotFoundEntityException::new);

        return selectedCoinMapper.toDTO(selectedCoin);
    }

    public List<ResponseSelectedCoinDTO> getAll(long selectedCoinMarketsStrategyId) {
        List<SelectedCoin> selectedCoins = selectedCoinRepository.findAllBySelectedCoinMarketsStrategyId(selectedCoinMarketsStrategyId)
                .orElseThrow(NotFoundEntityException::new);

        return selectedCoins.stream().map(selectedCoinMapper::toDTO).collect(Collectors.toList());
    }

    public void delete(long selectedCoinId) {
        selectedCoinRepository.deleteById(selectedCoinId);
    }
}
