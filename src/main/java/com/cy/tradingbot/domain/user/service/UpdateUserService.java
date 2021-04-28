package com.cy.tradingbot.domain.user.service;

import com.cy.tradingbot.domain.user.User;
import com.cy.tradingbot.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(long userId, int maxOfCandle, int numOfMovingAverageWindow, String coinList, int numOfCoinsForPurchase){
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        user.getTradingSettings().setMaxOfCandles(maxOfCandle);
        user.getTradingSettings().setNumOfMovingAverageWindow(numOfMovingAverageWindow);
        user.getTradingSettings().setCoins(coinList.trim());
        user.getTradingSettings().setNumOfCoinsForPurchase(numOfCoinsForPurchase);

        user = userRepository.save(user);
    }
}
