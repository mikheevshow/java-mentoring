package com.mentor.service;

import com.mentor.client.BitcoinPriceClient;
import com.mentor.model.Price;
import com.mentor.repository.BitcoinPriceDO;
import com.mentor.repository.BitcoinPriceRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
// Мы указали какие класс(ы) хотим протестировать. Во время исполнения
// теста, спринг сосздаст контекст, в который поместит бины, созданные
// при помощи аннотации @MockBean, а также создаст бин тестируемого класса,
// который можно заинжектить в тест через аннотацию @Autowired
@SpringBootTest(classes = BitcoinPriceServiceImpl.class)
public class BitcoinPriceServiceImplTest {

    @MockBean
    private BitcoinPriceClient rateClient;

    @MockBean
    private BitcoinPriceRepository repository;

    @Autowired
    private BitcoinPriceServiceImpl bitcoinPriceServiceImpl;

    @Test
    // Текст, который будет отображаться при прогонке тестов.
    // В разных командах разные практики, я люблю писать сюда суть того,
    // что проиходит в тесте, какой результат ожидается и по какой причине
    @DisplayName("Should return actual price value from database")
    public void getFreshPriceFromDatabaseTest() {

        Mockito.when(repository.getPrice(Mockito.eq("RUB"))).thenAnswer(i -> {
            final BitcoinPriceDO priceDO = new BitcoinPriceDO();

            priceDO.setPrice(BigDecimal.valueOf(2_000_000));
            priceDO.setId(123123L);
            priceDO.setLastUpdate(LocalDateTime.now());
            priceDO.setSymbol("RUB");

            return priceDO;
        });

        final Price price = bitcoinPriceServiceImpl.getPrice("RUB");


        // Хорошо, но если падает на каждой строке, то не очень
        Assertions.assertEquals(BigDecimal.valueOf(2_000_000), price.getPrice());
        Assertions.assertEquals("RUB", price.getSymbol());

        // Альтернатива 1 Soft Assertions из библиотеки assertj
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(2_000_000));
        softAssertions.assertThat(price.getSymbol()).isEqualTo("RUB");
        softAssertions.assertAll();

        // Альтернатива 2 изспользовать ожидаемый (expected) объект

        final Price expectedPrice = new Price();
        expectedPrice.setPrice(BigDecimal.valueOf(2_000_000));
        expectedPrice.setSymbol("RUB");


        assertThat(price).isEqualToIgnoringGivenFields(expectedPrice, "timestamp");

    }


    @Test
    @DisplayName("Should return actual price within call to external system, because not found in database")
    public void getFreshPriceFromExternalServiceBecauseNotFoundInDatabaseTest() {

    }

    @Test
    @DisplayName("Should return actual price within call " +
            "to external system, because the price from database is expired")
    public void getFreshPriceFromExternalServiceBecausePriceFromDatabaseExpiredTest() {
        // TODO Write test implementation here
    }

    @Test
    @DisplayName("Should throw an exception because can't get the price " +
            "neither from db nor from external service")
    public void throwAnExceptionWhenCantGetPriceTest() {
        // TODO Write test implementation here
    }
}
