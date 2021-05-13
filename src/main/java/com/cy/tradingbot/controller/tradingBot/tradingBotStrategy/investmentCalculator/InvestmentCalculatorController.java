package com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.investmentCalculator;

import com.cy.tradingbot.controller.tradingBot.tradingBotStrategy.TradingBotStrategyController;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestInvestmentCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class InvestmentCalculatorController<Entity extends InvestmentCalculator, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestInvestmentCalculatorDTO , ResponseDTO extends ResponseInvestmentCalculatorDTO >
    extends TradingBotStrategyController<Entity, InvestmentCalculatorRepository, Mapper, RequestDTO, ResponseDTO> {

    public InvestmentCalculatorController(TradingBotStrategyService<Entity, InvestmentCalculatorRepository, Mapper, RequestDTO, ResponseDTO> tradingBotStrategyService) {
        super(tradingBotStrategyService);
    }
}
