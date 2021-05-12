package com.cy.tradingbot.mapper;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import com.cy.tradingbot.dto.tradingBot.response.ResponseTradingBotDetailDTO;
import com.cy.tradingbot.mapper.getCoinsMarketsStrategy.GetCoinMarketsStrategyMapper;
import com.cy.tradingbot.mapper.investmentCalculator.InvestmentCalculatorMapper;
import com.cy.tradingbot.mapper.purchaseStrategy.PurchaseStrategyMapper;
import com.cy.tradingbot.mapper.sellStrategy.SellStrategyMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TradingBotDetailMapper {

    private final GetCoinMarketsStrategyMapper getCoinMarketsStrategyMapper;

    private final InvestmentCalculatorMapper investmentCalculatorMapper;
    private final PurchaseStrategyMapper purchaseStrategyMapper;
    private final SellStrategyMapper sellStrategyMapper;

    public TradingBotDetailMapper(GetCoinMarketsStrategyMapper getCoinMarketsStrategyMapper, InvestmentCalculatorMapper investmentCalculatorMapper, PurchaseStrategyMapper purchaseStrategyMapper, SellStrategyMapper sellStrategyMapper) {
        this.getCoinMarketsStrategyMapper = getCoinMarketsStrategyMapper;
        this.investmentCalculatorMapper = investmentCalculatorMapper;
        this.purchaseStrategyMapper = purchaseStrategyMapper;
        this.sellStrategyMapper = sellStrategyMapper;
    }

    public ResponseTradingBotDetailDTO toDTO(TradingBot entity) {
        ResponseTradingBotDetailDTO responseTradingBotDetailDTO = new ResponseTradingBotDetailDTO();
        responseTradingBotDetailDTO.setId(entity.getId());
        responseTradingBotDetailDTO.setName(entity.getName());
        responseTradingBotDetailDTO.setInvestmentRatio(entity.getInvestmentRatio());

        responseTradingBotDetailDTO.setGetCoinMarketsStrategy(getCoinMarketsStrategyMapper.toDTO(entity.getGetCoinMarketsStrategy()));
        responseTradingBotDetailDTO.setInvestmentCalculator(investmentCalculatorMapper.toDTO(entity.getInvestmentCalculator()));

        responseTradingBotDetailDTO.setPurchaseStrategy(entity.getPurchaseStrategy().stream()
                .map(purchaseStrategyMapper::toDTO)
                .collect(Collectors.toList())
        );

        responseTradingBotDetailDTO.setSellStrategy(entity.getSellStrategy().stream()
                .map(sellStrategyMapper::toDTO)
                .collect(Collectors.toList()));

        responseTradingBotDetailDTO.makeLinks();

        return responseTradingBotDetailDTO;
    }
}
