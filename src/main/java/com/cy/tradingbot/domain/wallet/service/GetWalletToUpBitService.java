package com.cy.tradingbot.domain.wallet.service;


import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.user.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetWalletToUpBitService implements GetWalletService {
    @Autowired
    private UpBitAPI upbitAPI;

    public List<Wallet> getWallets(Credential credential) {
        return upbitAPI.getAccounts(credential).orElseThrow(RuntimeException::new);
    }

    public Wallet getKrwWallet(Credential credential) {
        return getWallet("KRW", credential);
    }

    public Wallet getCoinWallet(String walletName, Credential credential) {
        return getWallet(walletName, credential);
    }

    public Wallet getWallet(String walletName, Credential credential) {
        return getWallets(credential).stream()
                .filter(wallet -> wallet.getCurrency().equals(walletName))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
    
    public Map<String, Wallet> getWalletHashTable(Credential credential) {
        Map<String, Wallet> walletHashtable = new HashMap<>();

        for (Wallet wallet : getWallets(credential)) {
            walletHashtable.put(wallet.getCurrency(), wallet);
        }

        return walletHashtable;
    }

}
