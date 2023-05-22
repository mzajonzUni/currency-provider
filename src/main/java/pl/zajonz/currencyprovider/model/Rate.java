package pl.zajonz.currencyprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Rate {

    private String currency;
    private String code;
    private Double bid;
    private Double ask;

}
