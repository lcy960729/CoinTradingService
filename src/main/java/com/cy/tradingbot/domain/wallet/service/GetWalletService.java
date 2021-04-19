package com.cy.tradingbot.domain.wallet.service;


import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.user.Credential;

import java.util.List;
import java.util.Map;


public interface GetWalletService {
    List<Wallet> getWallets(Credential credential);

    Wallet getKrwWallet(Credential credential);

    Wallet getCoinWallet(String walletName, Credential credential);

    Wallet getWallet(String walletName, Credential credential);

    Map<String, Wallet> getWalletHashTable(Credential credential);

}
