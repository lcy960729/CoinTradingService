package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.coinRatio.service;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.coinRatio.CoinsRatioCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service.InvestmentCalculatorService;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestCoinsRatioCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseCoinsRatioCalculatorDTO;
import com.cy.tradingbot.mapper.investmentCalculator.CoinsRatioCalculatorMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;
import org.springframework.stereotype.Service;

@Service
public class CoinsRatioCalculatorService extends InvestmentCalculatorService<CoinsRatioCalculator, CoinsRatioCalculatorMapper, RequestCoinsRatioCalculatorDTO, ResponseCoinsRatioCalculatorDTO> {

    protected CoinsRatioCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, CoinsRatioCalculatorMapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    @Override
    protected CoinsRatioCalculator createEntity(TradingBot tradingBot, RequestCoinsRatioCalculatorDTO requestCoinsRatioCalculatorDTO) {
        CoinsRatioCalculator coinsRatioCalculator = new CoinsRatioCalculator();
        coinsRatioCalculator.setTradingBot(tradingBot);

        updateEntity(coinsRatioCalculator, requestCoinsRatioCalculatorDTO);

        return coinsRatioCalculator;
    }

    @Override
    protected void updateEntity(CoinsRatioCalculator coinsRatioCalculator, RequestCoinsRatioCalculatorDTO requestCoinsRatioCalculatorDTO) {
        coinsRatioCalculator.setNumOfCoinsForPurchase(requestCoinsRatioCalculatorDTO.getNumOfCoinsForPurchase());
        setParent(coinsRatioCalculator, requestCoinsRatioCalculatorDTO.getParentId());
    }
}
