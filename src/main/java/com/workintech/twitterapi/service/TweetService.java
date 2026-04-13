package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CreateTweetDTO;
import com.workintech.twitterapi.entity.Tweet;

import java.util.List;

public interface TweetService {

    Tweet createTweet(CreateTweetDTO dto);

    List<Tweet> findByUserId(Long userId);

    Tweet findById(Long id);

    Tweet updateTweet(Long id, String content, Long userId);

    void deleteTweet(Long id, Long userId);
}
