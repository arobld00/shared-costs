package com.arobld00.autentia.domain.exceptions;

public class BalanceCalculationException extends RuntimeException {

    private static final String DESCRIPTION = "Cannot be completed";

    public BalanceCalculationException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
