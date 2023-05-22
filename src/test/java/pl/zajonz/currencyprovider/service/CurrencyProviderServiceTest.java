package pl.zajonz.currencyprovider.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajonz.currencyprovider.mapper.CurrencyMessageMapper;
import pl.zajonz.currencyprovider.model.CurrencyMessage;
import pl.zajonz.currencyprovider.model.NbpCurrencies;
import pl.zajonz.currencyprovider.model.Rate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyProviderServiceTest {

    @InjectMocks
    private CurrencyProviderService currencyProviderService;
    @Mock
    private CurrencyMessageMapper mapper;
    @Mock
    private CurrencySenderService currencySenderService;
    @Captor
    private ArgumentCaptor<CurrencyMessage> currencyMessageArgumentCaptor;
    @Captor
    private ArgumentCaptor<Rate> rateArgumentCaptor;


    @Test
    void testProcessCurrencies() {
        //given
        Rate rate = new Rate("euro", "EUR", 4.5, 4.6);
        NbpCurrencies nbpCurrencies = new NbpCurrencies(LocalDate.now(),
                List.of(rate));
        CurrencyMessage currencyMessage = new CurrencyMessage("EUR", LocalDate.now(), 4.5, 4.6);

        when(mapper.toCurrencyMessage(any(Rate.class), any(LocalDate.class))).thenReturn(currencyMessage);
        //when
        currencyProviderService.processCurrencies(nbpCurrencies);

        //then
        verify(mapper,times(nbpCurrencies.getRates().size())).toCurrencyMessage(
                rateArgumentCaptor.capture(),any(LocalDate.class));
        verify(currencySenderService, times(nbpCurrencies.getRates().size())).sendMessage(
                currencyMessageArgumentCaptor.capture());

        assertEquals(rate.getCurrency(),rateArgumentCaptor.getValue().getCurrency());
        assertEquals(rate.getBid(),rateArgumentCaptor.getValue().getBid());
        assertEquals(rate.getAsk(),rateArgumentCaptor.getValue().getAsk());

        assertEquals(currencyMessage.getCurrency(),currencyMessageArgumentCaptor.getValue().getCurrency());
        assertEquals(currencyMessage.getAsk(),currencyMessageArgumentCaptor.getValue().getAsk());
        assertEquals(currencyMessage.getBid(),currencyMessageArgumentCaptor.getValue().getBid());
    }
}