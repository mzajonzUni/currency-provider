package pl.zajonz.currencyprovider.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.model.CurrenciesMessage;
import pl.zajonz.currencyprovider.model.CurrenciesNbp;
import pl.zajonz.currencyprovider.model.Rates;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyProviderServiceTest {

    @InjectMocks
    private CurrencyProviderService currencyProviderService;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private MessageConverter messageConverter;
    @Mock
    private NbpApiClient nbpApiClient;
    @Captor
    private ArgumentCaptor<Message> argumentCaptor;

    @Test
    void testGetAllCurrencies() {
        //given
        CurrenciesNbp currenciesNbp = new CurrenciesNbp(LocalDate.now(),
                List.of(new Rates("Poland","PLN",4.4,4.5),
                        new Rates("Poland","PLN",4.4,4.5)));
        CurrenciesMessage currenciesMessage = new CurrenciesMessage("PLN",LocalDate.now(),4.4,4.5);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("type", "currencies");
        Message message = new Message(currenciesMessage.toString().getBytes(), messageProperties);


        when(nbpApiClient.getAllCurrencies()).thenReturn(List.of(currenciesNbp));
        when(messageConverter.toMessage(any(CurrenciesMessage.class),any(MessageProperties.class))).thenReturn(message);

        //when
        currencyProviderService.getAllCurrencies();

        //then
        verify(nbpApiClient, times(1)).getAllCurrencies();
        verify(rabbitTemplate, times(2))
                .convertAndSend(anyString(), anyString(), argumentCaptor.capture());
        Message capturedMessage = argumentCaptor.getValue();
        assertNotNull(capturedMessage);
        assertEquals("currencies", capturedMessage.getMessageProperties().getHeaders().get("type"));
    }
}