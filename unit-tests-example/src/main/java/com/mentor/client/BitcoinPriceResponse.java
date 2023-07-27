package com.mentor.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinPriceResponse {
    private String symbol;
    private BigDecimal price;
    private Long timestamp;
}
