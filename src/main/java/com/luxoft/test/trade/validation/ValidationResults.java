package com.luxoft.test.trade.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResults {
    private List<ValidationResult> results;

    public ValidationResults(){
        this.results = new ArrayList<>();
    }

    public List<ValidationResult> getResults() {
        return results;
    }

    public void setResults(List<ValidationResult> results) {
        this.results = results;
    }

    public void appendValidationResult(ValidationResult result){
        results.add(result);
    }
}
