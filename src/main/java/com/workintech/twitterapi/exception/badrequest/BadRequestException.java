package com.workintech.twitterapi.exception.badrequest;

import com.workintech.twitterapi.exception.base.BaseException;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }
}
