package com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy;

import com.cy.tradingbot.domain.tradingBot.tradingBotStrategy.getCoinMarketsStrategy.topChangeRateCoinMarketsStrategy.TopChangeRateCoinMarketsStrategy;
import com.cy.tradingbot.repository.GetCoinMarketsStrategyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GetCoinMarketsStrategyEntityListenerTest {

    @Autowired
    private GetCoinMarketsStrategyRepository getCoinMarketsStrategyRepository;

    @Test
    @DisplayName("GetCoinMarkets전략의 엔티티 리스너가 영속전 coinExchange를 정상적으로 주입하는 테스트")
    void prePersist() {
        // given
        TopChangeRateCoinMarketsStrategy topChangeRateCoinMarketsStrategy = new TopChangeRateCoinMarketsStrategy();

        // when
        topChangeRateCoinMarketsStrategy = getCoinMarketsStrategyRepository.save(topChangeRateCoinMarketsStrategy);

        // then
        assertThat(topChangeRateCoinMarketsStrategy.getCoinExchange()).isNotNull();
    }
}