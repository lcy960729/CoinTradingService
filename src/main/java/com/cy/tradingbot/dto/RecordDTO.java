package com.cy.tradingbot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordDTO {
    private String dateTime;
    private String balance;
    private String yield;

    public void setYield(Double yield) {
        yield = Math.round(yield * 1000) / 1000.0;

        this.yield = String.format("%,.3f", yield) + "%";
    }
}
