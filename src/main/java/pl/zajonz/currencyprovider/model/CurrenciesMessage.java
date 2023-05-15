package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CurrenciesMessage{

    private String currency;
    private LocalDate timestamp;
    private Double bid;
    private Double ask;

    public static CurrenciesMessage convert(Rates rates, LocalDate tradingDate){
        return CurrenciesMessage.builder()
                .currency(rates.getCode())
                .timestamp(tradingDate )
                .bid(rates.getBid())
                .ask(rates.getAsk())
                .build();
    }

}
