package com.mentor.service;

import com.mentor.client.BitcoinPriceClient;
import com.mentor.model.Price;
import com.mentor.repository.BitcoinPriceDO;
import com.mentor.repository.BitcoinPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BitcoinPriceServiceImpl implements BitcoinPriceService {

    @Value("${db.price.expiration-period-millis}")
    private int expirationMillis;

    private final BitcoinPriceClient rateClient;
    private final BitcoinPriceRepository repository;

    @Override
    public Price getPrice(String symbol) {
        final BitcoinPriceDO priceFromDB = repository.getPrice(symbol);
    }
}
