package com.mentor.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BitcoinPriceRepositoryImpl implements BitcoinPriceRepository {

    @Override
    public BitcoinPriceDO getPrice(String symbol) {
        log.info("Start fetching bitcoin price relate to symbol {}", symbol);
        return null;
    }

    public void upsertPrice(String symbol, BigDecimal price) {
        log.info("Starting upsert price relate to symbol {}, new price {}", symbol, price);
    }
}
