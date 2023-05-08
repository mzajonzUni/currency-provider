package pl.zajonz.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajonz.currencyprovider.client.NbpApiClient;
import pl.zajonz.currencyprovider.model.Currencies;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
public class CurrencyProviderService {

    private final NbpApiClient nbpApiClient;

    @GetMapping
    public Currencies getAllCurrencies(){
        return nbpApiClient.getAllCurrencies();
    }

}
