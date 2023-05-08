package pl.zajonz.currencyprovider.client;

import feign.RequestLine;
import pl.zajonz.currencyprovider.model.Currencies;

public interface NbpApiClient {

    @RequestLine("GET /tables/A/today/")
    Currencies getAllCurrencies();


}
