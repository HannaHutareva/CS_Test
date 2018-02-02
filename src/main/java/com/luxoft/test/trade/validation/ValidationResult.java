package com.luxoft.test.trade.validation;

import com.luxoft.test.trade.model.Trade;

public class ValidationResult {
    private String validationMessage;
    private Trade trade;

    public ValidationResult(String validationMessage, Trade trade) {
        this.validationMessage = validationMessage;
        this.trade = trade;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String message) {
        this.validationMessage = message;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }
}
