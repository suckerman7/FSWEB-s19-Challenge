package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestParam Long userId, @RequestParam Long tweetId) {

        likeService.likeTweet(userId, tweetId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestParam Long userId, @RequestParam Long tweetId) {

        likeService.dislikeTweet(userId, tweetId);

        return ResponseEntity.ok().build();
    }
}
