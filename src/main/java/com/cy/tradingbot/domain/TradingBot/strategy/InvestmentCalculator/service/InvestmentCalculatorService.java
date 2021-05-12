package com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.service;

import com.cy.tradingbot.domain.TradingBot.strategy.InvestmentCalculator.InvestmentCalculator;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.response.ResponseInvestmentCalculatorDTO;
import com.cy.tradingbot.dto.tradingBotStrategy.investmentCalculator.request.RequestInvestmentCalculatorDTO;
import com.cy.tradingbot.exception.NotFoundEntityException;
import com.cy.tradingbot.repository.InvestmentCalculatorRepository;

public abstract class InvestmentCalculatorService<K extends RequestInvestmentCalculatorDTO, V extends ResponseInvestmentCalculatorDTO> {
    protected final InvestmentCalculatorRepository investmentCalculatorRepository;

    public InvestmentCalculatorService(InvestmentCalculatorRepository investmentCalculatorRepository) {
        this.investmentCalculatorRepository = investmentCalculatorRepository;
    }

    public abstract V create(long tradingBotId, K requestInvestmentCalculatorDTO);

    public abstract V update(long investmentCalculatorId, K requestInvestmentCalculatorDTO);

    public void delete(long investmentCalculatorId) {
        investmentCalculatorRepository.deleteById(investmentCalculatorId);
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

            InvestmentCalculator parent = investmentCalculatorRepository.findById(parentId).orElseThrow(NotFoundEntityException::new);

            investmentCalculator.setChild(parent.getChild());
            if (investmentCalculator.getChild() != null) {
                investmentCalculator.getChild().setParent(investmentCalculator);
            }

            investmentCalculator.setParent(parent);
            parent.setChild(investmentCalculator);
        }
        // parent investmentCalculator
        // 1 -> 2 -> 3
        // 1 -> 3 -> 2
    }

    public abstract V get(long investmentCalculatorId);
}
