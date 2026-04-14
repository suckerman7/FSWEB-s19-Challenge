package com.workintech.twitterapi.service;

public interface LikeService {

    void likeTweet(Long userId, Long tweetId);

    void dislikeTweet(Long userId, Long tweetId);

    void toggleLike(Long userId, Long tweetId);
}
