package com.workintech.twitterapi;

import com.workintech.twitterapi.dto.create.CreateTweetDTO;
import com.workintech.twitterapi.dto.response.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.service.TweetServiceImpl;
import com.workintech.twitterapi.util.mapper.TweetMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    @InjectMocks
    private TweetServiceImpl tweetService;

    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private TweetMapper tweetMapper;

    @Mock
    private SecurityContext securityContext;

    @Test
    void shouldCreateTweet() {

        User user = new User();
        user.setId(1L);

        CreateTweetDTO dto = new CreateTweetDTO();
        dto.setContent("Hello World!");

        Tweet tweet = new Tweet();
        Tweet savedTweet = new Tweet();

        TweetResponseDTO response = mock(TweetResponseDTO.class);

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(user);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);

        SecurityContextHolder.setContext(securityContext);

        when(tweetMapper.toEntity(dto)).thenReturn(tweet);
        when(tweetRepository.save(any())).thenReturn(savedTweet);
        when(tweetMapper.toDto(any())).thenReturn(response);

        tweetService.createTweet(dto);

        verify(tweetRepository, times(1)).save(any());
    }

}
