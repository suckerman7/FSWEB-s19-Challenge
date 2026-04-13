package com.workintech.twitterapi.exception.notfound;

import com.workintech.twitterapi.exception.base.BaseException;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }
}
