package com.cy.tradingbot.configure.spring;

import com.cy.tradingbot.domain.coin.Coin;
import com.cy.tradingbot.domain.coin.coinExchange.CoinExchange;
import com.cy.tradingbot.domain.coin.coinExchange.service.UpdateCandlesService;
import com.cy.tradingbot.domain.coin.service.GetAllMarketsService;
import com.cy.tradingbot.repository.CoinRepository;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpringService implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {
    private final CoinRepository coinRepository;
    private final GetAllMarketsService getAllMarketsService;
    private final CoinExchange coinExchange;
    private final UpdateCandlesService updateCandlesService;

    public SpringService(CoinRepository coinRepository, GetAllMarketsService getAllMarketsService, CoinExchange coinExchange, UpdateCandlesService updateCandlesService) {
        this.coinRepository = coinRepository;
        this.getAllMarketsService = getAllMarketsService;
        this.coinExchange = coinExchange;
        this.updateCandlesService = updateCandlesService;
    }

    @PostConstruct
    private void init() {
        System.err.println("PostConstruct annotation으로 빈이 완전히 생성된 후에 한 번 수행될 메서드에 붙입니다.");
    }

    @Override
    public void run(String... args) {
        System.err.println("commandLineRunner 인터페이스 구현 메서드입니다. '애플리케이션'이 실행될 때 '한 번' 실행됩니다.");

        List<Coin> coins = getAllMarketsService.getAllMarkets();

        List<Coin> notExistCoins = coins.stream().filter(coin -> {
            Optional<Coin> optionalCoin = coinRepository.findByCoinNameLike(coin.getCoinName());
            return !optionalCoin.isPresent();
        }).collect(Collectors.toList());

        coinRepository.saveAll(notExistCoins);

        coins = coinRepository.findAll();

        coinExchange.addCoinMarkets(coins);

        updateCandlesService.updateCandles();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.err.println("ApplicationListener<ContextClosedEvent> 인터페이스 구현 메서드 입니다. '애플리케이션'이 죽었을 때 '한 번' 실행됩니다.");
        System.err.println("이벤트 발생 시간(timestamp) : " + event.getTimestamp());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("InitializingBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 생성될 때 마다 호출되는 메서드 입니다.");
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("DisposableBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 소멸될 때 마다 호출되는 메서드입니다");
    }
}
