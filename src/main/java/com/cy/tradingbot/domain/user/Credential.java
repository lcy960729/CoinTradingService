package com.cy.tradingbot.domain.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Credential {
    @Column(name = "accessKey")
    private String accessKey;

    @Column(name = "secretKey")
    private String secretKey;
}
