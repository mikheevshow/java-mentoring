package com.mentor.service;

import com.mentor.model.Price;

public interface BitcoinPriceService {

    Price getPrice(String symbol);

}
