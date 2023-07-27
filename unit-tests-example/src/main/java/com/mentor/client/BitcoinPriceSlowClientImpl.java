package com.mentor.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import static java.net.http.HttpResponse.BodyHandlers.ofByteArray;

@Component
@RequiredArgsConstructor
public class BitcoinPriceSlowClientImpl implements BitcoinPriceClient {

    private final static String API_KEY_HEADER_NAME = "x-api-key";

    @Value("${price-client.slowness-emulation.enabled}")
    private boolean emulateSlowness;
    @Value("${price-client.slowness-emulation.millis}")
    private int waitMillis;
    @Value("${price-client.api-key}")
    private String apiKey;

    private final HttpClient bitcoinPriceHttpClient;
    private final ObjectMapper objectMapper;

    @Override
    public BitcoinPriceResponse getPrice(String symbol) {

        Objects.requireNonNull(symbol);

        if (emulateSlowness) {
            try {
                Thread.currentThread().wait(waitMillis);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }

        final HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.api-ninjas.com/v1/cryptoprice?symbol=BTC" + symbol))
                .header(API_KEY_HEADER_NAME, apiKey)
                .build();

        try {
            final HttpResponse<byte[]> response = bitcoinPriceHttpClient.send(request, ofByteArray());
            return objectMapper.readValue(response.body(), BitcoinPriceResponse.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
