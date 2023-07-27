package com.mentor.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {
    private final BigDecimal price;
    private final String symbol;
}
