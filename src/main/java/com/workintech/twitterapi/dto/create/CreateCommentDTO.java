package com.workintech.twitterapi.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentDTO {

    @NotBlank
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long tweetId;
}
