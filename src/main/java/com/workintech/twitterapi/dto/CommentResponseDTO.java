package com.workintech.twitterapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDTO {

    private Long id;
    private String content;
    private Long userId;
    private Long tweetId;
    private LocalDateTime createdAt;

}
