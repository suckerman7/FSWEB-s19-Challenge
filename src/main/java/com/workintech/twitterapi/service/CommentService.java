package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.response.CommentResponseDTO;
import com.workintech.twitterapi.dto.create.CreateCommentDTO;

public interface CommentService {

    CommentResponseDTO createComment(CreateCommentDTO dto);

    CommentResponseDTO updateComment(Long commentId, String content);

    void deleteComment(Long commentId);
}
