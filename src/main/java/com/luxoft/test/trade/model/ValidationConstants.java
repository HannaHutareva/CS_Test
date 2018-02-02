package com.luxoft.test.trade.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public final class ValidationConstants {
    public static final LocalDate CURRENT_DATE = LocalDate.of(2016, 10, 9);
    public static final String LEGAL_ENTITY = "CS Zurich";
    public static final List<String> ISO_CODES = createIsoCodesList();

    public enum TRADES {
        Spot, Forward, VanillaOption
    }

    public enum CUSTOMER {
        PLUTO1, PLUTO2
    }

    public enum STYLE {
        AMERICAN, EUROPEAN
    }

    private static List createIsoCodesList() {
        List<String> isoCodes = new ArrayList<>();
        Currency.getAvailableCurrencies().forEach(currency -> isoCodes.add(currency.getCurrencyCode()));
        return isoCodes;
    }


}
