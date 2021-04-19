package com.cy.tradingbot.domain.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Credential {
    @Column(name = "accessKey")
    private String accessKey;

    @Column(name = "secretKey")
    private String secretKey;
}
