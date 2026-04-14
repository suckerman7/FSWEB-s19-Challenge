package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.badrequest.BadRequestException;
import com.workintech.twitterapi.exception.notfound.TweetNotFoundException;
import com.workintech.twitterapi.exception.notfound.UserNotFoundException;
import com.workintech.twitterapi.repository.LikeRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public void likeTweet(Long userId, Long tweetId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Kullanıcı bulunamadı! id: " + userId
                ));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(
                        "Tweet bulunamadı! id: " + tweetId
                ));

        boolean alreadyLiked = likeRepository
                .findByUserIdAndTweetId(userId, tweetId)
                .isPresent();

        if(alreadyLiked) {
            throw new BadRequestException("Bu tweet zaten beğenilmiş.");
        }

        Like like = Like.builder()
                .user(user)
                .tweet(tweet)
                .build();

        likeRepository.save(like);
    }

    @Override
    public void dislikeTweet(Long userId, Long tweetId) {

        Like like = likeRepository
                .findByUserIdAndTweetId(userId, tweetId)
                .orElseThrow(() -> new BadRequestException(
                        "Beğeni bulunamadı! id: " + tweetId
                ));

        likeRepository.delete(like);
    }

    @Override
    public void toggleLike(Long userId, Long tweetId) {

        Optional<Like> optionalLike = likeRepository.findByUserIdAndTweetId(userId, tweetId);

        if(optionalLike.isPresent()) {
            likeRepository.delete(optionalLike.get());
            return;
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı! id: " + userId));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id: " + tweetId));

        Like like = Like.builder()
                .user(user)
                .tweet(tweet)
                .build();

        likeRepository.save(like);
    }
}
