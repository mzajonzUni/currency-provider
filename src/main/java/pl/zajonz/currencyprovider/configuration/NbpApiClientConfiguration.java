package pl.zajonz.currencyprovider.configuration;

import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zajonz.currencyprovider.adapter.LocalDateTypeAdapter;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.configuration.properties.NbpApiProperties;

import java.time.LocalDate;

@Configuration
public class NbpApiClientConfiguration {

    @Bean
    public NbpApiClient currencyProviderApiClient(NbpApiProperties properties){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        GsonDecoder decoder = new GsonDecoder(gsonBuilder.create());

        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(decoder)
                .logger(new Slf4jLogger(NbpApiClient.class))
                .logLevel(Logger.Level.FULL)
                .target(NbpApiClient.class, properties.getBaseUrl());
    }

}
