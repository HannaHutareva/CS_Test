package com.luxoft.test.trade.validation.validators;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.utils.DateUtils;
import com.luxoft.test.trade.validation.ValidationResult;
import com.luxoft.test.trade.validation.ValidationResults;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


import static com.luxoft.test.trade.model.ValidationConstants.CUSTOMER;
import static com.luxoft.test.trade.model.ValidationConstants.ISO_CODES;
import static com.luxoft.test.trade.model.ValidationMessages.*;

@Service
public class CommonValidator implements Validator {

    @Override
    public void validate(Trade trade, ValidationResults results) {
        validateValueDate(trade, results);
        validateCustomer(trade, results);
        validateCurrency(trade, results);
        if(isDatesExist(trade)){
            validateWorkDay(trade, results);
        }
    }

    private void validateValueDate(Trade trade, ValidationResults results) {
        if (isDatesExist(trade)) {
            LocalDate valueDate = LocalDate.parse(trade.getValueDate());
            LocalDate tradeDate = LocalDate.parse(trade.getTradeDate());
            if (valueDate.isBefore(tradeDate)) {
                results.appendValidationResult(new ValidationResult(INVALID_VALUE_DATE, trade));
            }
        }

    }

    private void validateCustomer(Trade trade, ValidationResults results) {
        if (!EnumUtils.isValidEnum(CUSTOMER.class, trade.getCustomer())) {
            results.appendValidationResult(new ValidationResult(UNSUPPORTED_CUSTOMER, trade));
        }
    }

    private void validateCurrency(Trade trade, ValidationResults results) {
        String currency1 = trade.getCcyPair().substring(0, 3);
        String currency2 = trade.getCcyPair().substring(3, 6);
        if (!ISO_CODES.contains(currency1) && ISO_CODES.contains(currency2)) {
            results.appendValidationResult(new ValidationResult(INVALID_ISO_CODE, trade));
        }
    }


    private boolean isDatesExist(Trade trade) {
        return StringUtils.isBlank(trade.getTradeDate()) || StringUtils.isBlank(trade.getValueDate()) ? false : true;
    }

    private void validateWorkDay(Trade trade, ValidationResults results) {
        if (DateUtils.isNonWorkingDay(trade.getValueDate())) {
            results.appendValidationResult(new ValidationResult(NON_WORKING_DAY, trade));
        }
    }
}
