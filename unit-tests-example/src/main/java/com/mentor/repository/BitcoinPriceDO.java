package com.mentor.repository;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BitcoinPriceDO {
    private Long id;
    private BigDecimal price;
    private String symbol;
    private LocalDateTime lastUpdate;
}
