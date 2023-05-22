package pl.zajonz.currencyprovider.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zajonz.currencyprovider.model.CurrencyMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {

    @Value("${currencies.queue}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers");
    }

    @Bean
    public Binding currenciesBinding(Queue queue, HeadersExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .where("type")
                .matches("currencies");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Jackson2JsonMessageConverter json = new Jackson2JsonMessageConverter(mapper);
        json.setClassMapper(classMapper());
        return json;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("CurrenciesMessage", CurrencyMessage.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

}
