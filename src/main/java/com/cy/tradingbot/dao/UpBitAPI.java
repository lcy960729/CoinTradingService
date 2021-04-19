package com.cy.tradingbot.dao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cy.tradingbot.domain.candle.Candle;
import com.cy.tradingbot.domain.orderProccesor.orderResult.OrderResult;
import com.cy.tradingbot.domain.orderProccesor.orderSheet.OrderSheet;
import com.cy.tradingbot.domain.wallet.Wallet;
import com.cy.tradingbot.domain.user.Credential;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class UpBitAPI {
    private final RestTemplate restTemplate;

    public UpBitAPI() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        restTemplate = new RestTemplate(factory);
    }

    public String mapToQueryString(MultiValueMap<String, String> map) {
        if (map == null || map.isEmpty()) return null;

        StringBuilder stringBuilder = new StringBuilder();

        map.forEach((k, v) -> v.forEach(s -> {
                    stringBuilder.append(k);
                    if (v.size() > 1)
                        stringBuilder.append("[]");
                    stringBuilder.append("=");
                    stringBuilder.append(s);
                    stringBuilder.append("&");
                })
        );

        if (!map.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    private String getSecretKey(MultiValueMap<String, String> parameters, Credential credential) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String queryHash = "";
        String query = mapToQueryString(parameters);

        if (query != null && !query.isEmpty()) {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(query.getBytes("utf8"));

            queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
        }

        Algorithm algorithm = Algorithm.HMAC256(credential.getSecretKey());
        String jwtToken = JWT.create()
                .withClaim("access_key", credential.getAccessKey())
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        return "Bearer " + jwtToken;
    }

    private final String uri = "https://api.upbit.com/v1";

    public Optional<List<Candle>> getDayCandle(String coinName, int count) {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/candles/days");
        uriComponentsBuilder.queryParam("market",
                "KRW-" + coinName);
        uriComponentsBuilder.queryParam("count", count);

        ResponseEntity<List<Candle>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Candle>>() {
        });

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        return Optional.ofNullable(responseEntity.getBody());
    }

    public Optional<List<Candle>> getTicker(Set<String> coinName) {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/ticker");
        uriComponentsBuilder.queryParam("markets", coinName);

        ResponseEntity<List<Candle>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Candle>>() {
        });

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        return Optional.ofNullable(responseEntity.getBody());
    }

    public Optional<List<Wallet>> getAccounts(Credential credential) {

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.set("Authorization", getSecretKey(null, credential));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/accounts");

        ResponseEntity<List<Wallet>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Wallet>>() {
        });

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        return Optional.ofNullable(responseEntity.getBody());
    }

    public Optional<OrderResult> order(OrderSheet orderSheet) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("market", orderSheet.getCoinName());
        parameters.add("side", orderSheet.getSide());
        if (orderSheet.getVolume() != null)
            parameters.add("volume", String.valueOf(orderSheet.getVolume()));
        if (orderSheet.getPrice() != null)
            parameters.add("price", String.valueOf(orderSheet.getPrice()));
        parameters.add("ord_type", orderSheet.getOrderType());

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.set("Authorization", getSecretKey(parameters, orderSheet.getCredential()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpEntity<?> entity = new HttpEntity<>(parameters, httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/orders");

        ResponseEntity<OrderResult> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.POST, entity, OrderResult.class);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED)
            throw new RuntimeException();

        OrderResult ret = responseEntity.getBody();

        ret.setOrderSheet(orderSheet);

        return Optional.ofNullable(responseEntity.getBody());
    }

    public Optional<OrderResult> getOrder(OrderResult orderResult) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("uuid", orderResult.getUuid());

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.set("Authorization", getSecretKey(parameters, orderResult.getOrderSheet().getCredential()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/order");
        uriComponentsBuilder.queryParams(parameters);

        ResponseEntity<OrderResult> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, OrderResult.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        OrderResult ret = responseEntity.getBody();

        ret.setOrderSheet(orderResult.getOrderSheet());

        return Optional.ofNullable(responseEntity.getBody());
    }

    public void deleteOrder(OrderResult orderResult) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("uuid", orderResult.getUuid());

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.set("Authorization", getSecretKey(parameters, orderResult.getOrderSheet().getCredential()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/order");
        uriComponentsBuilder.queryParams(parameters);

        ResponseEntity<OrderResult> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.DELETE, entity, OrderResult.class);

    }

//    public Optional<List<OrderBook>> getOrderBook(List<String> coinNameList) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/orderbook");
//
//
//        uriComponentsBuilder.queryParam("markets", coinNameList);
//
//        ResponseEntity<List<OrderBook>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<OrderBook>>() {
//        });
//
//        if (responseEntity.getStatusCode() != HttpStatus.OK)
//            return Optional.empty();
//
//        return Optional.ofNullable(responseEntity.getBody());
//    }
//
//    public Optional<List<Candle>> getMinuteCandle(String coinName, int minute, int count) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/candles/minutes/" + minute);
//        uriComponentsBuilder.queryParam("market", coinName);
//        uriComponentsBuilder.queryParam("count", count);
//
//        ResponseEntity<List<Candle>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Candle>>() {
//        });
//
//        if (responseEntity.getStatusCode() != HttpStatus.OK)
//            return Optional.empty();
//
//        return Optional.ofNullable(responseEntity.getBody());
//    }
//
//    public Optional<List<CoinMarket>> getAllCoin() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri + "/market/all");
//
//        ResponseEntity<List<CoinMarket>> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<CoinMarket>>() {
//        });
//
//        if (responseEntity.getStatusCode() != HttpStatus.OK)
//            return Optional.empty();
//
//        return Optional.ofNullable(responseEntity.getBody());
//    }
}
