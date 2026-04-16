package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;

public interface RetweetService {

    void retweet(Long userId, Long tweetId);

    void deleteRetweet(Long retweetId, Long userId);
}
