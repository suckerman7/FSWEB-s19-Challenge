package com.workintech.twitterapi;

import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.LikeRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import com.workintech.twitterapi.service.LikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @InjectMocks
    private LikeServiceImpl likeService;

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TweetRepository tweetRepository;

    @Test
    void shouldLikeTweet() {

        User user = new User();
        Tweet tweet = new Tweet();
        user.setId(1L);
        tweet.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        when(likeRepository.findByUserIdAndTweetId(1L,1L)).thenReturn(Optional.empty());

        likeService.likeTweet(1L, 1L);

        verify(likeRepository, times(1)).save(any());
    }
}
