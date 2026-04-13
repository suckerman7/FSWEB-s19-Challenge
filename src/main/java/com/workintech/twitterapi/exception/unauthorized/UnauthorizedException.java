package com.workintech.twitterapi.exception.unauthorized;

import com.workintech.twitterapi.exception.base.BaseException;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
