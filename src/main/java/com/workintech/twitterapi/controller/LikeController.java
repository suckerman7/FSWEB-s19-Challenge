package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.LikeRequestDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody LikeRequestDTO dto) {

        Long userId = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();

        likeService.likeTweet(userId, dto.getTweetId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody LikeRequestDTO dto) {

        Long userId = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();

        likeService.dislikeTweet(userId, dto.getTweetId());

        return ResponseEntity.ok().build();
    }
}
