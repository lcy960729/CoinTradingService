package com.cy.tradingbot.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestUserDTO {
    private String userName;
    private String password;
}
