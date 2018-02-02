package com.luxoft.test.trade.validation.validators;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.validation.ValidationResults;

public interface Validator {

    void validate(Trade trade, ValidationResults results);

}
