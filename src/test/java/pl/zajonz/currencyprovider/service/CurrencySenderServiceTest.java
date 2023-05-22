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
import pl.zajonz.currencyprovider.model.CurrencyMessage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencySenderServiceTest {

    @InjectMocks
    private CurrencySenderService currencySenderService;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private MessageConverter messageConverter;
    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    void testSendMessage() {
        //given
        CurrencyMessage currencyMessage = new CurrencyMessage("EUR", LocalDate.now(), 4.5, 4.6);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("type", "currencies");
        Message message = new Message(currencyMessage.toString().getBytes(), messageProperties);

        when(messageConverter.toMessage(any(CurrencyMessage.class), any(MessageProperties.class))).thenReturn(message);
        //when
        currencySenderService.sendMessage(currencyMessage);

        //then
        verify(rabbitTemplate, times(1))
                .convertAndSend(anyString(),anyString(), messageArgumentCaptor.capture());

        assertNotNull(messageArgumentCaptor);
        assertEquals("currencies", messageArgumentCaptor.getValue().getMessageProperties().getHeaders()
                .get("type"));
    }
}