package com.workintech.twitterapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TweetResponseDTO {

    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
}
