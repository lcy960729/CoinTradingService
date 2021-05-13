package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.tradingBot.service.TradingBotService;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.service.TradingBotStrategyService;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBot.tradingBotStrategy.investmentCalculator.request.RequestInvestmentCalculatorDTO;
import com.cy.tradingbot.mapper.EntityMapper;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;

public abstract class InvestmentCalculatorService<Entity extends InvestmentCalculator, Mapper extends EntityMapper<ResponseDTO, Entity>, RequestDTO extends RequestInvestmentCalculatorDTO, ResponseDTO extends ResponseInvestmentCalculatorDTO>
        extends TradingBotStrategyService<Entity, InvestmentCalculatorRepository, Mapper, RequestDTO, ResponseDTO> {

    protected InvestmentCalculatorService(TradingBotService tradingBotService, InvestmentCalculatorRepository repository, Mapper mapper) {
        super(tradingBotService, repository, mapper);
    }

    protected void setParent(InvestmentCalculator investmentCalculator, Long parentId) {
        if (parentId == null) {
            InvestmentCalculator cur = investmentCalculator;

            while (cur.getParent() != null) {
                cur = cur.getParent();
            }

            if (investmentCalculator.getParent() != null)
                investmentCalculator.getParent().setChild(investmentCalculator.getChild());

            if (investmentCalculator.getChild() != null)
                investmentCalculator.getChild().setParent(investmentCalculator.getParent());

            investmentCalculator.setParent(null);

            if (cur != investmentCalculator) {
                investmentCalculator.setChild(cur);
                cur.setParent(investmentCalculator);
            }
        } else {
            if (investmentCalculator.getParent() != null && parentId.equals(investmentCalculator.getParent().getId()))
                return;

            User user = investmentCalculator.getTradingBot().getUser();
            long tradingBotId = investmentCalculator.getTradingBot().getId();

            InvestmentCalculator parent = getEntity(user, tradingBotId, parentId);

            investmentCalculator.setChild(parent.getChild());
            if (investmentCalculator.getChild() != null) {
                investmentCalculator.getChild().setParent(investmentCalculator);
            }

            investmentCalculator.setParent(parent);
            parent.setChild(investmentCalculator);
        }
    }
}
