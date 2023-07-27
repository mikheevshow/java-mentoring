package com.mentor.service;

import com.mentor.client.BitcoinPriceClient;
import com.mentor.repository.BitcoinPriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
// Мы указали какие класс(ы) хотим протестировать. Во время исполнения
// теста, спринг сосздаст контекст, в который поместит бины, созданные
// при помощи аннотации @MockBean, а также создаст бин тестируемого класса,
// который можно заинжектить в тест через аннотацию @Autowired
@SpringBootTest(classes = BitcoinPriceServiceImpl.class)
public class BitcoinPriceServiceImplTest {

    // Создаем бины-фальшивки зависимостей, которые требуются для
    // работы сервиса BitcoinPriceServiceImpl.
    // В дальнейшем мы будем определять поведение этих фальшивок,
    // чтобы увидететь как отрабатывает реализация нашего сервиса
    @MockBean
    private BitcoinPriceClient rateClient;

    @MockBean
    private BitcoinPriceRepository repository;

    // Так как мы в начале указали, какой класс собираемся тестировать,
    // то теперь можем смело инжектить его в тест
    @Autowired
    private BitcoinPriceServiceImpl bitcoinPriceServiceImpl;

    @Test
    // Текст, который будет отображаться при прогонке тестов.
    // В разных командах разные практики, я люблю писать сюда суть того,
    // что проиходит в тесте, какой результат ожидается и по какой причине
    @DisplayName("Should return actual price value from database")
    public void getFreshPriceFromDatabaseTest() {

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
