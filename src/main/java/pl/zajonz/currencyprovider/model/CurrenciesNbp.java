package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CurrenciesNbp {

    private LocalDate tradingDate;
    private List<Rates> rates;

}
