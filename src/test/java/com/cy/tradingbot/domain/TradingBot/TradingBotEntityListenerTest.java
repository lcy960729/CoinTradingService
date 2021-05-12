package com.cy.tradingbot.domain.TradingBot;

import com.cy.tradingbot.repository.TradingBotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradingBotEntityListenerTest {

    @Autowired
    private TradingBotRepository tradingBotRepository;

    @Test
    @DisplayName("TradingBot 엔티티 리스너가 영속전 scheduler를 정상적으로 주입하는 테스트")
    void perPersist() {
        //given
        TradingBot tradingBot = new TradingBot();

        //when
        tradingBot = tradingBotRepository.save(tradingBot);

        //then
        assertThat(tradingBot.getTradingSignalObserver()).isNotNull();
    }
}