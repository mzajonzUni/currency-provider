package pl.zajonz.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zajonz.currencyprovider.client.NbpApiClient;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledProcessCurrencies {

    private final NbpApiClient nbpApiClient;
    private final CurrencyProviderService currencyProviderService;

    @Scheduled(cron = "${upload.time}")
    public void getAllCurrencies(){
        currencyProviderService.processCurrencies(nbpApiClient.getAllCurrencies().get(0));
    }

}
