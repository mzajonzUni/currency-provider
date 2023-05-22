package pl.zajonz.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajonz.currencyprovider.mapper.CurrencyMessageMapper;
import pl.zajonz.currencyprovider.model.NbpCurrencies;

@Service
@RequiredArgsConstructor
public class CurrencyProviderService {

    private final CurrencySenderService currencySenderService;
    private final CurrencyMessageMapper mapper;

    public void processCurrencies(NbpCurrencies currencies) {
        currencies.getRates()
                .forEach(rate -> currencySenderService.sendMessage(
                        mapper.toCurrencyMessage(rate, currencies.getTradingDate())));
    }
}
