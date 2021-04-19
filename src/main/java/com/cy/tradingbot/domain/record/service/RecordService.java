package com.cy.tradingbot.domain.record.service;

import com.cy.tradingbot.util.TimeCalculator;
import com.cy.tradingbot.domain.record.Record;
import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.dto.RecordDTO;
import com.cy.tradingbot.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void write(User user, double balance) {
        Record record = new Record();
        record.setUser(user);
        record.setBalance(balance);
        record.setDateTime(TimeCalculator.now());

        recordRepository.save(record);
    }

    public List<RecordDTO> getAll(long userId) {
        LocalDateTime end = TimeCalculator.now().plusDays(1);
        LocalDateTime start = LocalDateTime.of(end.toLocalDate(), LocalTime.of(0, 0, 0)).minusMonths(1);

        List<Record> records = recordRepository.findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, start, end);

        if (records.isEmpty()) return new ArrayList<>();

        List<RecordDTO> recordDTOList = new ArrayList<>();
        for (int i = 0; i < records.size() - 1; i++) {
            RecordDTO recordDTO = records.get(i).toDTO();

            Double nowBalance = records.get(i).getBalance();
            Double yesterdayBalance = records.get(i + 1).getBalance();

            recordDTO.setYield(((nowBalance / yesterdayBalance) - 1.0) * 100.0);

            recordDTOList.add(recordDTO);
        }
        recordDTOList.add(records.get(records.size() - 1).toDTO());

        return recordDTOList;
    }
}
