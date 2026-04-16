package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.create.CreateTweetDTO;
import com.workintech.twitterapi.dto.response.TweetResponseDTO;
import com.workintech.twitterapi.dto.create.UpdateTweetDTO;

import java.util.List;

public interface TweetService {

    TweetResponseDTO createTweet(CreateTweetDTO dto);

    List<TweetResponseDTO> findByUserId(Long userId);

    TweetResponseDTO findById(Long id);

    TweetResponseDTO updateTweet(Long id, UpdateTweetDTO dto);

    void deleteTweet(Long id);
}
