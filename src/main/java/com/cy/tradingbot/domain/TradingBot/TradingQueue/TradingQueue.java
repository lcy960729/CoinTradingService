package com.cy.tradingbot.domain.tradingBot.TradingQueue;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Component
public class TradingQueue {
    private final Set<TradingBot> runningTradingBot = new HashSet<>();
    private final Queue<TradingBot> tradingBotQueue = new LinkedList<>();

    public void start(TradingBot tradingBot){
        runningTradingBot.add(tradingBot);
        tradingBotQueue.add(tradingBot);
    }

    public void stop(TradingBot tradingBot){
        runningTradingBot.remove(tradingBot);
    }

    public void add(TradingBot tradingBot){
        tradingBotQueue.add(tradingBot);
    }

    public TradingBot get() {
        return tradingBotQueue.poll();
    }

    public boolean isRunning(TradingBot tradingBot){
        return runningTradingBot.contains(tradingBot);
    }
}
