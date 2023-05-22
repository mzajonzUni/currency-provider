package pl.zajonz.currencyprovider.client;

import feign.RequestLine;
import pl.zajonz.currencyprovider.model.NbpCurrencies;

import java.util.List;

public interface NbpApiClient {

    @RequestLine("GET /tables/C/")
    List<NbpCurrencies> getAllCurrencies();


}
