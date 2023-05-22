package pl.zajonz.currencyprovider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zajonz.currencyprovider.service.ScheduledProcessCurrencies;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
public class CurrencyProviderController {

    private final ScheduledProcessCurrencies scheduledProcessCurrencies;

    @GetMapping
    public void getAllCurrencies() {
        scheduledProcessCurrencies.getAllCurrencies();
    }


}
