package com.luxoft.test.trade.validation.validators;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.validation.ValidationResult;
import com.luxoft.test.trade.validation.ValidationResults;

import java.time.LocalDate;

import static com.luxoft.test.trade.model.ValidationMessages.INVALID_VALUE_DATE_FOR_PRODUCT_TYPE;

public class SpotForwardValidator extends CommonValidator {

    public void validate(Trade trade, ValidationResults results) {
        super.validate(trade, results);
        validateValueDate(trade, results);
    }

    private void validateValueDate(Trade trade, ValidationResults results) {
        LocalDate valueDate = LocalDate.parse(trade.getValueDate());
        LocalDate tradeDate = LocalDate.parse(trade.getTradeDate());
        if (!valueDate.isAfter(tradeDate)) {
            results.appendValidationResult(new ValidationResult(INVALID_VALUE_DATE_FOR_PRODUCT_TYPE, trade));
        }
    }

}
