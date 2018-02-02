package com.luxoft.test.trade.service;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.validation.ValidationResults;
import com.luxoft.test.trade.validation.validators.OptionsValidator;
import com.luxoft.test.trade.validation.validators.SpotForwardValidator;
import com.luxoft.test.trade.validation.validators.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.luxoft.test.trade.model.ValidationConstants.TRADES.*;

@Service
public class TradeValidationService {

    private static final Logger LOG = LoggerFactory.getLogger(TradeValidationService.class);

    private Map<String, Validator> validatorsMap;

    private void setupValidators() {
        validatorsMap = new HashMap<>();
        validatorsMap.put(Forward.name(), new SpotForwardValidator());
        validatorsMap.put(VanillaOption.name(), new OptionsValidator());
        validatorsMap.put(Spot.name(), new SpotForwardValidator());
    }

    public ValidationResults validate(List<Trade> tradesList) {
        ValidationResults results = new ValidationResults();
        setupValidators();
        tradesList.forEach(trade -> {
            Validator validator = validatorsMap.get(trade.getType());
            validator.validate(trade, results);
        });
        results.getResults().forEach(validationResult -> {
            LOG.debug(validationResult.getValidationMessage());
        });
        return results;
    }
}
