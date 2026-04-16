package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.create.CreateTweetDTO;
import com.workintech.twitterapi.dto.response.TweetResponseDTO;
import com.workintech.twitterapi.dto.create.UpdateTweetDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.notfound.TweetNotFoundException;
import com.workintech.twitterapi.exception.unauthorized.TweetUnauthorizedException;
import com.workintech.twitterapi.repository.CommentRepository;
import com.workintech.twitterapi.repository.LikeRepository;
import com.workintech.twitterapi.repository.RetweetRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.util.mapper.TweetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final RetweetRepository retweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public TweetResponseDTO createTweet(CreateTweetDTO dto) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Tweet tweet = tweetMapper.toEntity(dto);
        tweet.setUser(user);

        return tweetMapper.toDto(tweetRepository.save(tweet));
    }

    @Override
    public List<TweetResponseDTO> findByUserId(Long userId) {

        return tweetMapper.toDtoList(
                tweetRepository.findByUserId(userId)
        );
    }

    @Override
    public TweetResponseDTO findById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));

        return tweetMapper.toDto(tweet);
    }

    @Override
    public TweetResponseDTO updateTweet(Long id, UpdateTweetDTO dto) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));

        if(!tweet.getUser().getId().equals(user.getId())) {
            throw new TweetUnauthorizedException("Bu tweet'i güncelleme yetkiniz yok.");
        }

        tweetMapper.updateTweetFromDto(dto, tweet);

        return tweetMapper.toDto(tweetRepository.save(tweet));
    }

    @Override
    public void deleteTweet(Long id) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! id:" + id));

        if(!tweet.getUser().getId().equals(user.getId())) {
            throw new TweetUnauthorizedException("Bu tweet'i silme yetkiniz yok.");
        }

        commentRepository.deleteAllByTweetId(id);
        likeRepository.deleteAllByTweetId(id);
        retweetRepository.deleteAllByTweetId(id);

        tweetRepository.delete(tweet);
    }
}
