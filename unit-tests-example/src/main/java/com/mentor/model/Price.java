package com.mentor.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {
    private BigDecimal price;
    private String symbol;
}
