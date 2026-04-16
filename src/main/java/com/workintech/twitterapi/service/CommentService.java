package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.dto.CreateCommentDTO;

public interface CommentService {

    CommentResponseDTO createComment(CreateCommentDTO dto);

    CommentResponseDTO updateComment(Long commentId, String content);

    void deleteComment(Long commentId);
}
