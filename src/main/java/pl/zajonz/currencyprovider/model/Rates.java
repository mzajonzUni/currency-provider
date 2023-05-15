package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Rates {

    private String currency;
    private String code;
    private Double bid;
    private Double ask;

}
