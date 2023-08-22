package com.mentor.service;

import com.mentor.client.BitcoinPriceClient;
import com.mentor.client.BitcoinPriceResponse;
import com.mentor.model.Price;
import com.mentor.repository.BitcoinPriceDO;
import com.mentor.repository.BitcoinPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.*;

@Service
@RequiredArgsConstructor
public class BitcoinPriceServiceImpl implements BitcoinPriceService {

    @Value("${db.price.expiration-period-millis}")
    private long expirationMillis;

    private final BitcoinPriceClient rateClient;
    private final BitcoinPriceRepository repository;

    @Override
    public Price getPrice(String symbol) {

        final BitcoinPriceDO priceFromDB = repository.getPrice(symbol);

        final Price price = new Price();
        price.setPrice(priceFromDB.getPrice());
        price.setSymbol(priceFromDB.getSymbol());

        return price;
    }
}
