package com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy;

import com.cy.tradingbot.domain.TradingBot.strategy.sellStrategy.closeChapterStartegy.CloseChapterStrategy;
import com.cy.tradingbot.repository.SellStrategyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CloseChapterStrategyEntityListenerTest {

    @Autowired
    private SellStrategyRepository sellStrategyRepository;

    @Test
    @DisplayName("CloseChapter전략의 엔티티 리스너가 영속전 scheduler를 정상적으로 주입하는 테스트")
    void prePersist() {
        // given
        CloseChapterStrategy closeChapterStrategy = new CloseChapterStrategy();

        // when
        closeChapterStrategy = sellStrategyRepository.save(closeChapterStrategy);

        // then
        assertThat(closeChapterStrategy.getCloseChapterScheduler()).isNotNull();
    }
}