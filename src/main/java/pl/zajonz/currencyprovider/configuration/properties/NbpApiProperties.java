package pl.zajonz.currencyprovider.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "nbp.api")
@Setter
@Getter
public class NbpApiProperties {
    private String baseUrl;
}
