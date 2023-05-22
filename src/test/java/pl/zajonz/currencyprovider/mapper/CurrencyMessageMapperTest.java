package pl.zajonz.currencyprovider.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.zajonz.currencyprovider.model.CurrencyMessage;
import pl.zajonz.currencyprovider.model.Rate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMessageMapperTest {

    CurrencyMessageMapper mapper = Mappers.getMapper(CurrencyMessageMapper.class);

    @Test
    void testToCurrencyMessage() {
        //given
        LocalDate tradingDate = LocalDate.now();
        Rate rate = new Rate("euro", "EUR", 4.5, 4.6);
        CurrencyMessage currencyMessage = new CurrencyMessage("EUR", tradingDate, 4.5, 4.6);

        //when
        CurrencyMessage mapped = mapper.toCurrencyMessage(rate,tradingDate);

        //then
        assertEquals(currencyMessage.getCurrency(),mapped.getCurrency());
        assertEquals(currencyMessage.getAsk(),mapped.getAsk());
        assertEquals(currencyMessage.getBid(),mapped.getBid());
        assertEquals(currencyMessage.getTimestamp(),mapped.getTimestamp());
    }
}