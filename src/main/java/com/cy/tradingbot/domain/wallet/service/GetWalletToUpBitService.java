package com.cy.tradingbot.domain.wallet.service;


import com.cy.tradingbot.dao.UpBitAPI;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.user.Credential;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetWalletToUpBitService implements GetWalletService {
    private final UpBitAPI upbitAPI;

    public GetWalletToUpBitService(UpBitAPI upbitAPI) {
        this.upbitAPI = upbitAPI;
    }

    public List<Wallet> getWallets(Credential credential) {
        return upbitAPI.getAccounts(credential).orElseThrow(RuntimeException::new);
    }

    public Wallet getCoinWallet(String coinName, Credential credential) {
        return getWalletHashTable(credential).get(coinName);
    }
    
    public Map<String, Wallet> getWalletHashTable(Credential credential) {
        Map<String, Wallet> walletHashtable = new HashMap<>();

        for (Wallet wallet : getWallets(credential)) {
            walletHashtable.put(wallet.getCurrency(), wallet);
        }

        return walletHashtable;
    }
}
