package com.cy.tradingbot.domain.user.service;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(long userId){
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }
}
