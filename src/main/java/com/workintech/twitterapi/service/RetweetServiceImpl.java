package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.badrequest.BadRequestException;
import com.workintech.twitterapi.exception.notfound.RetweetNotFoundException;
import com.workintech.twitterapi.exception.notfound.TweetNotFoundException;
import com.workintech.twitterapi.exception.notfound.UserNotFoundException;
import com.workintech.twitterapi.exception.unauthorized.UnauthorizedException;
import com.workintech.twitterapi.repository.RetweetRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public Retweet createRetweet(Long userId, Long tweetId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı! id: " + userId));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id: " + tweetId));

        boolean alreadyRetweeted = retweetRepository
                .findByUserIdAndTweetId(userId, tweetId)
                .isPresent();

        if(alreadyRetweeted) {
            throw new BadRequestException("Bu tweet zaten retweet edilmiş.");
        }

        Retweet retweet = Retweet.builder()
                .user(user)
                .tweet(tweet)
                .build();

        return retweetRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(Long userId, Long tweetId) {

        Retweet retweet = retweetRepository.findByUserIdAndTweetId(userId, tweetId)
                .orElseThrow(() -> new RetweetNotFoundException("Retweet bulunamadı!"));

        if(!retweet.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Bu retweet'i silme yetkiniz yok.");
        }

        retweetRepository.delete(retweet);
    }
}
