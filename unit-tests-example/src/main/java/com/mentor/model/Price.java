package com.mentor.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Price {
    private BigDecimal price;
    private String symbol;
    private LocalDateTime timestamp;
}
