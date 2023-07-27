package com.mentor.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class BitcoinPriceHttpClientConfiguration {

    @Bean
    private HttpClient bitcoinPriceHttpClient() {
        return HttpClient.newHttpClient();
    }
}
