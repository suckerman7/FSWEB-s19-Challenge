package com.workintech.twitterapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.workintech.twitterapi.dto.create.CreateCommentDTO;
import com.workintech.twitterapi.dto.response.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.CommentRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import com.workintech.twitterapi.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCreateComment() {

        CreateCommentDTO dto = new CreateCommentDTO();
        dto.setContent("test");
        dto.setUserId(1L);
        dto.setTweetId(1L);

        User user = new User();
        Tweet tweet = new Tweet();

        Comment saved = new Comment();
        saved.setId(1L);
        saved.setContent("test");
        saved.setUser(user);
        saved.setTweet(tweet);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        when(commentRepository.save(any())).thenReturn(saved);

        CommentResponseDTO result = commentService.createComment(dto);

        assertNotNull(result);
        assertEquals("test", result.getContent());
    }
}
