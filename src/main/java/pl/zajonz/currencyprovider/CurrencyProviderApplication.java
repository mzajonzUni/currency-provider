package pl.zajonz.currencyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.zajonz.currencyprovider.configuration.properties.NbpApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(NbpApiProperties.class)
public class CurrencyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyProviderApplication.class, args);
    }

}
