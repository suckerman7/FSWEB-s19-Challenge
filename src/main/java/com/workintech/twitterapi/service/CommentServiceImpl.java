package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CreateCommentDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.notfound.CommentNotFoundException;
import com.workintech.twitterapi.exception.notfound.TweetNotFoundException;
import com.workintech.twitterapi.exception.notfound.UserNotFoundException;
import com.workintech.twitterapi.exception.unauthorized.UnauthorizedException;
import com.workintech.twitterapi.repository.CommentRepository;
import com.workintech.twitterapi.repository.TweetRepository;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(CreateCommentDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı! Id: " + dto.getUserId()));

        Tweet tweet = tweetRepository.findById(dto.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı! Id:" + dto.getTweetId()));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .user(user)
                .tweet(tweet)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, String content, Long userId) {

        Comment comment = getCommentOrThrow(commentId);

        if(!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Bu yorumu güncelleme yetkiniz yok.");
        }

        comment.setContent(content);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

        Comment comment = getCommentOrThrow(commentId);

        Long commentOwnerId = comment.getUser().getId();
        Long tweetOwnerId = comment.getTweet().getUser().getId();

        if(!commentOwnerId.equals(userId) && !tweetOwnerId.equals(userId)) {
            throw new UnauthorizedException("Bu yorumu silme yetkiniz yok.");
        }

        commentRepository.delete(comment);
    }

    private Comment getCommentOrThrow(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Yorum bulunamadı! Id:" + id));
    }
}
