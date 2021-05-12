package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.TradingBot;
import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.CoinsRatioCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestCoinsRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseCoinsRatioCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.mapper.investmentCalculator.CoinsRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import com.cy.tradingbot.repository.TradingBotRepository;
import org.springframework.stereotype.Service;

@Service
public class CoinsRatioCalculatorService extends InvestmentCalculatorService<RequestCoinsRatioCalculatorDTO,ResponseCoinsRatioCalculatorDTO> {
    private final TradingBotRepository tradingBotRepository;
    private final CoinsRatioCalculatorMapper coinsRatioCalculatorMapper;

    public CoinsRatioCalculatorService(TradingBotRepository tradingBotRepository, InvestmentCalculatorRepository investmentCalculatorRepository, CoinsRatioCalculatorMapper coinsRatioCalculatorMapper) {
        super(investmentCalculatorRepository);
        this.tradingBotRepository = tradingBotRepository;
        this.coinsRatioCalculatorMapper = coinsRatioCalculatorMapper;
    }

    @Override
    public ResponseCoinsRatioCalculatorDTO update(long investmentCalculatorId, RequestCoinsRatioCalculatorDTO coinsRatioCalculatorDTO) {
        CoinsRatioCalculator coinsRatioCalculator = ((CoinsRatioCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));

        coinsRatioCalculator.setNumOfCoinsForPurchase(coinsRatioCalculatorDTO.getNumOfCoinsForPurchase());

        setParent(coinsRatioCalculator, coinsRatioCalculatorDTO.getParentId());

        coinsRatioCalculator = investmentCalculatorRepository.save(coinsRatioCalculator);

        return coinsRatioCalculatorMapper.toDTO(coinsRatioCalculator);
    }

    public ResponseCoinsRatioCalculatorDTO create(long tradingBotId, RequestCoinsRatioCalculatorDTO coinsRatioCalculatorDTO) {
        TradingBot tradingBot = tradingBotRepository.findById(tradingBotId).orElseThrow(NotFoundEntityException::new);

        CoinsRatioCalculator coinsRatioCalculator = new CoinsRatioCalculator();
        coinsRatioCalculator.setNumOfCoinsForPurchase(coinsRatioCalculatorDTO.getNumOfCoinsForPurchase());
        coinsRatioCalculator.setTradingBot(tradingBot);

        setParent(coinsRatioCalculator, coinsRatioCalculatorDTO.getParentId());

        coinsRatioCalculator = investmentCalculatorRepository.save(coinsRatioCalculator);

        return coinsRatioCalculatorMapper.toDTO(coinsRatioCalculator);
    }

    @Override
    public ResponseCoinsRatioCalculatorDTO get(long investmentCalculatorId) {
        CoinsRatioCalculator coinsRatioCalculator = ((CoinsRatioCalculator) investmentCalculatorRepository.findById(investmentCalculatorId).orElseThrow(NotFoundEntityException::new));
        return coinsRatioCalculatorMapper.toDTO(coinsRatioCalculator);
    }
}
