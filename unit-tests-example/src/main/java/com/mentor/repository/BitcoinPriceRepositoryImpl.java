package com.mentor.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BitcoinPriceRepositoryImpl implements BitcoinPriceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public BitcoinPriceDO getPrice(String symbol) {
        log.info("Start fetching bitcoin price relate to symbol {}", symbol);
        return jdbcTemplate.queryForObject(
                "SELECT id, price, symbol, last_update FROM price WHERE symbol = ?",
                new BitcoinPriceRowMapper(),
                symbol
        );
    }

    public void upsertPrice(String symbol, BigDecimal price) {
        log.info("Starting upsert price relate to symbol {}, new price {}", symbol, price);
        "MERGE INTO price "
    }
}
