package com.cy.tradingbot.mapper.investmentCalculator;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.*;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class InvestmentCalculatorMapper implements EntityMapper<ResponseInvestmentCalculatorDTO, InvestmentCalculator> {
    private final CoinsRatioCalculatorMapper coinsRatioCalculatorMapper;
    private final FixedRatioCalculatorMapper fixedRatioCalculatorMapper;
    private final FixedAmountCalculatorMapper fixedAmountCalculatorMapper;
    private final ScoreOfMovingAverageCalculatorMapper scoreOfMovingAverageCalculatorMapper;
    private final YesterdayVolatilityCalculatorMapper yesterdayVolatilityCalculatorMapper;

    public InvestmentCalculatorMapper(CoinsRatioCalculatorMapper coinsRatioCalculatorMapper, FixedRatioCalculatorMapper fixedRatioCalculatorMapper, FixedAmountCalculatorMapper fixedAmountCalculatorMapper, ScoreOfMovingAverageCalculatorMapper scoreOfMovingAverageCalculatorMapper, YesterdayVolatilityCalculatorMapper yesterdayVolatilityCalculatorMapper) {
        this.coinsRatioCalculatorMapper = coinsRatioCalculatorMapper;
        this.fixedRatioCalculatorMapper = fixedRatioCalculatorMapper;
        this.fixedAmountCalculatorMapper = fixedAmountCalculatorMapper;
        this.scoreOfMovingAverageCalculatorMapper = scoreOfMovingAverageCalculatorMapper;
        this.yesterdayVolatilityCalculatorMapper = yesterdayVolatilityCalculatorMapper;
    }

    public ResponseInvestmentCalculatorDTO toDTO(InvestmentCalculator investmentCalculator) {
        if (investmentCalculator == null) return null;

        if (investmentCalculator instanceof CoinsRatioCalculator) {
            return coinsRatioCalculatorMapper.toDTO(((CoinsRatioCalculator) investmentCalculator));
        } else if (investmentCalculator instanceof FixedAmountCalculator) {
            return fixedAmountCalculatorMapper.toDTO(((FixedAmountCalculator) investmentCalculator));
        } else if (investmentCalculator instanceof FixedRatioCalculator) {
            return fixedRatioCalculatorMapper.toDTO(((FixedRatioCalculator) investmentCalculator));
        } else if (investmentCalculator instanceof ScoreOfMovingAverageCalculator) {
            return scoreOfMovingAverageCalculatorMapper.toDTO(((ScoreOfMovingAverageCalculator) investmentCalculator));
        } else if (investmentCalculator instanceof YesterdayVolatilityCalculator) {
            return yesterdayVolatilityCalculatorMapper.toDTO(((YesterdayVolatilityCalculator) investmentCalculator));
        }

        return null;
    }
}
