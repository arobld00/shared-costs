package com.arobld00.autentia.domain.exceptions;

public class PhoneAlreadyExistException extends RuntimeException {

    private static final String DESCRIPTION = "Conflict Exception (409)";

    public PhoneAlreadyExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
