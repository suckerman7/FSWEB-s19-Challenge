package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CreateTweetDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.notfound.TweetNotFoundException;
import com.workintech.twitterapi.exception.notfound.UserNotFoundException;
import com.workintech.twitterapi.exception.unauthorized.TweetUnauthorizedException;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Tweet createTweet(CreateTweetDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı! id:" + dto.getUserId()));

        Tweet tweet = Tweet.builder()
                .content(dto.getContent())
                .user(user)
                .build();

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet findById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));
    }

    @Override
    public Tweet updateTweet(Long id, String content, Long userId) {

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));

        if(!tweet.getUser().getId().equals(userId)) {
            throw new TweetUnauthorizedException("Bu tweet'i güncelleme yetkiniz yok.");
        }

        tweet.setContent(content);

        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Long id, Long userId) {

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));

        if(!tweet.getUser().getId().equals(userId)) {
            throw new TweetUnauthorizedException("Bu tweet'i silme yetkiniz yok.");
        }

        tweetRepository.delete(tweet);
    }
}
