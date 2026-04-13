package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CreateCommentDTO;
import com.workintech.twitterapi.entity.Comment;

public interface CommentService {

    Comment createComment(CreateCommentDTO dto);

    Comment updateComment(Long commentId, String content, Long userId);

    void deleteComment(Long commentId, Long userId);
}
