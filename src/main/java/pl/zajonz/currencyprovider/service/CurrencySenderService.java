package pl.zajonz.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyprovider.model.CurrencyMessage;

@Service
@RequiredArgsConstructor
public class CurrencySenderService {
    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    public void sendMessage(CurrencyMessage currencyMessage){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("type", "currencies");
        Message message =  messageConverter.toMessage(currencyMessage, messageProperties);
        rabbitTemplate.convertAndSend("exchange.headers", "", message);
    }


}
