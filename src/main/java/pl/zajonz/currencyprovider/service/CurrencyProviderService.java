package pl.zajonz.currencyprovider.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.model.CurrenciesNbp;
import pl.zajonz.currencyprovider.model.CurrenciesMessage;
import pl.zajonz.currencyprovider.model.Rates;

@Service
@AllArgsConstructor
@EnableScheduling
public class CurrencyProviderService {

    private final NbpApiClient nbpApiClient;
    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    @Scheduled(cron = "${upload.time}")
    public void getAllCurrencies() {
        CurrenciesNbp currencies = nbpApiClient.getAllCurrencies().get(0);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("type", "currencies");

        for (Rates rates : currencies.getRates()) {
            Message message =  messageConverter.toMessage(
                    CurrenciesMessage.convert(rates, currencies.getTradingDate()), messageProperties);
            rabbitTemplate.convertAndSend("exchange.headers", "", message);
        }
    }

}
