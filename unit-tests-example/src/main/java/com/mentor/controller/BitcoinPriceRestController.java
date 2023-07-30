package com.mentor.controller;

import com.mentor.model.Price;
import com.mentor.service.BitcoinPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BitcoinPriceRestController {

    private final BitcoinPriceService bitcoinPriceService;

    @GetMapping("/api/v1/get-price")
    public Price getPrice(@RequestParam(required = false) String symbol) {
        return bitcoinPriceService.getPrice(symbol != null ? symbol : "USD");
    }

}
