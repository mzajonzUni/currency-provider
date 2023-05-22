package pl.zajonz.currencyprovider.service;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class ScheduledProcessCurrenciesTest {

    @SpyBean
    private ScheduledProcessCurrencies scheduledProcessCurrencies;

    @Test
    public void testGetAllCurrencies() {
        await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() -> verify(scheduledProcessCurrencies,
                        times(1)).getAllCurrencies());
    }
}