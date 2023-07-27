package com.mentor.controller;

import com.mentor.model.Price;
import com.mentor.service.BitcoinPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BitcoinPriceRestController {

    private final BitcoinPriceService bitcoinPriceService;

    @GetMapping("/api/v1/get-price")
    public Price getPrice() {
        return bitcoinPriceService.getPrice("USD");
    }

}
