package com.workintech.twitterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTweetDTO {

    @NotBlank
    @Size(min = 1, max = 280)
    private String content;

    @NotNull
    private Long userId;

}
