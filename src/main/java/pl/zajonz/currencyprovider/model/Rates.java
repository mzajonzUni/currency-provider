package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rates {

    private String currency;
    private String code;
    private Double bid;
    private Double ask;

}
