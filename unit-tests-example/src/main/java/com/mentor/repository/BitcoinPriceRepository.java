package com.mentor.repository;

public interface BitcoinPriceRepository {

    BitcoinPriceDO getPrice(String symbol);

}
