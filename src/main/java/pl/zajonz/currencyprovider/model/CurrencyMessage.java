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
public class CurrencyMessage {

    private String currency;
    private LocalDate timestamp;
    private Double bid;
    private Double ask;

}
