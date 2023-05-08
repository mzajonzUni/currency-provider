package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Currencies {

    private LocalDate effectiveDate;
    private Rates rates;

}
