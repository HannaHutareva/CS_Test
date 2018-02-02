package com.luxoft.test.trade;

import com.luxoft.test.TestProjectApplication;
import com.luxoft.test.trade.model.ErrorInfo;
import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.service.TradeValidationService;
import com.luxoft.test.trade.validation.ValidationResults;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/trades", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Trade information validation service")
public class TradeValidationResource {

    private static final Logger LOG = LoggerFactory.getLogger(TradeValidationResource.class);

    @Autowired
    private TradeValidationService validationService;

    @PostMapping(value = "/validation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ValidationResults validateTrades(@Valid @RequestBody List<Trade> asList) {
        return validationService.validate(asList);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ErrorInfo handleBadRequest(Exception ex) {
        LOG.error(ex.getLocalizedMessage());
        return new ErrorInfo(ex);
    }
}
