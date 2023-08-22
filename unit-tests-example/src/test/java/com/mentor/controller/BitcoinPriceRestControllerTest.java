package com.mentor.controller;

import com.mentor.model.Price;
import com.mentor.service.BitcoinPriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Пример теста для слоя RestController
 */
@WebMvcTest(BitcoinPriceRestController.class) //  Указать контроллер, который мы собираемся тестировать
@ExtendWith(SpringExtension.class)
public class BitcoinPriceRestControllerTest {

    /**
     * Нужно создать заглушки для зависимосьтей, которые инжектятся
     * в контроллеры
     */
    @MockBean
    private BitcoinPriceService bitcoinPriceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return bitcoin price to USD if symbol param not passed")
    public void shouldReturnPriceRelateToUSD() throws Exception {

        when(bitcoinPriceService.getPrice(eq("USD"))).thenThrow();

        final MvcResult mvcResult = mockMvc
                .perform(get("/api/v1/get-price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(valueOf(20_000)))
                .andExpect(jsonPath("$.symbol").value("USD"))
                .andReturn();

        verify(bitcoinPriceService, times(1)).getPrice(eq("USD"));
    }

}
