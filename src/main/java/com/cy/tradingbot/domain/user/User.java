package com.cy.tradingbot.domain.user;

import com.cy.tradingbot.domain.log.Log;
import com.cy.tradingbot.domain.record.Record;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "User")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Embedded
    private Credential credential;

    @Embedded
    private TradingSettings tradingSettings;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Record> recordList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Log> logList;

    public List<String> getCoinList() {
        return Arrays.stream(tradingSettings.getCoins().trim().split(" ")).collect(Collectors.toList());
    }
}