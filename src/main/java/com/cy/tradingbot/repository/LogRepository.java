package com.cy.tradingbot.repository;

import com.cy.tradingbot.domain.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(long userId, LocalDateTime startTime, LocalDateTime endTime);
}
