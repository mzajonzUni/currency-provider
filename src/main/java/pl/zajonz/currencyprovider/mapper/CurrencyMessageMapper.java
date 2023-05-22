package pl.zajonz.currencyprovider.mapper;

import org.mapstruct.Mapper;
import pl.zajonz.currencyprovider.model.CurrencyMessage;
import pl.zajonz.currencyprovider.model.Rate;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface CurrencyMessageMapper {

     default CurrencyMessage toCurrencyMessage(Rate rate, LocalDate tradingDate) {
        return CurrencyMessage.builder()
                .currency(rate.getCode())
                .timestamp(tradingDate)
                .bid(rate.getBid())
                .ask(rate.getAsk())
                .build();
    }

}
