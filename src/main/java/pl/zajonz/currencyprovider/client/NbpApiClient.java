package pl.zajonz.currencyprovider.client;

import feign.RequestLine;
import pl.zajonz.currencyprovider.model.CurrenciesNbp;

import java.util.List;

public interface NbpApiClient {

    @RequestLine("GET /tables/C/")
    List<CurrenciesNbp> getAllCurrencies();


}
