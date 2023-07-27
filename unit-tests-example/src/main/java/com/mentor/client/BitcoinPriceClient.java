package com.mentor.client;

import com.mentor.model.Price;

public interface BitcoinPriceClient {
    BitcoinPriceResponse getPrice(String symbol);

}
