package com.luxoft.test.trade.validation.validators;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.model.ValidationConstants;
import com.luxoft.test.trade.validation.ValidationResult;
import com.luxoft.test.trade.validation.ValidationResults;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDate;

import static com.luxoft.test.trade.model.ValidationMessages.INVALID_EXCERCISE_START_DATE;
import static com.luxoft.test.trade.model.ValidationMessages.INVALID_EXPIRE_AND_PREMIUM_DATE;
import static com.luxoft.test.trade.model.ValidationMessages.INVALID_STYLE;
import static com.luxoft.test.trade.model.ValidationConstants.STYLE.AMERICAN;

public class OptionsValidator extends CommonValidator {

    public void validate(Trade trade, ValidationResults results) {
        super.validate(trade, results);
        validateOptionStyle(trade, results);
        validateDeliveryDate(trade, results);
        if(trade.getStyle().equals(AMERICAN)){
            validateExcerciseStartDate(trade, results) ;
        }
    }

    private void validateOptionStyle(Trade trade, ValidationResults results) {
        if (!EnumUtils.isValidEnum(ValidationConstants.STYLE.class, trade.getStyle())) {
            results.appendValidationResult(new ValidationResult(INVALID_STYLE, trade));
        }
    }

    private void validateExcerciseStartDate(Trade trade, ValidationResults results) {
        LocalDate excerciseStartDate = LocalDate.parse(trade.getExcerciseStartDate());
        LocalDate expiryDate = LocalDate.parse(trade.getExpiryDate());
        LocalDate tradeDate = LocalDate.parse(trade.getTradeDate());
        if (!excerciseStartDate.isAfter(tradeDate) && excerciseStartDate.isBefore(expiryDate)) {
            results.appendValidationResult(new ValidationResult(INVALID_EXCERCISE_START_DATE, trade));
        }
    }

    private void validateDeliveryDate(Trade trade, ValidationResults results) {
        LocalDate premiumDate = LocalDate.parse(trade.getPremiumDate());
        LocalDate expiryDate = LocalDate.parse(trade.getExpiryDate());
        LocalDate deliveryDate = LocalDate.parse(trade.getDeliveryDate());
        if (!expiryDate.isBefore(deliveryDate) || premiumDate.isBefore(deliveryDate)) {
            results.appendValidationResult(new ValidationResult(INVALID_EXPIRE_AND_PREMIUM_DATE, trade));
        }
    }

}