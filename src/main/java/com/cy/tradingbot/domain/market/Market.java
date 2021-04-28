package com.cy.tradingbot.domain.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Market {
    @JsonProperty("market")
    private String market;
}