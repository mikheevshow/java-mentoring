package com.mentor.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BitcoinPriceRowMapper implements RowMapper<BitcoinPriceDO> {
    @Override
    public BitcoinPriceDO mapRow(ResultSet rs, int rowNum) throws SQLException {

        final BitcoinPriceDO bitcoinPriceDO = new BitcoinPriceDO();
        bitcoinPriceDO.setId(rs.getLong("id"));
        bitcoinPriceDO.setPrice(rs.getBigDecimal("price"));
        bitcoinPriceDO.setSymbol(rs.getString("symbol"));
        bitcoinPriceDO.setLastUpdate(rs.getTimestamp("last_update").toLocalDateTime());

        return bitcoinPriceDO;
    }
}
