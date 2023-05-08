package pl.zajonz.currencyprovider.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.model.Currencies;

@Service
@AllArgsConstructor
public class CurrencyProviderService {

    private final NbpApiClient nbpApiClient;

    public Currencies getAllCurrencies(){
        return nbpApiClient.getAllCurrencies();
    }

}
