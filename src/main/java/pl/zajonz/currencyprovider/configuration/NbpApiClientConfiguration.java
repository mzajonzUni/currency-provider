package pl.zajonz.currencyprovider.configuration;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.configuration.properties.NbpApiProperties;

@Configuration
public class NbpApiClientConfiguration {

    @Bean
    public NbpApiClient currencyProviderApiClient(NbpApiProperties properties){

        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(NbpApiClient.class))
                .logLevel(Logger.Level.FULL)
                .target(NbpApiClient.class, properties.getBaseUrl());

    }

}
