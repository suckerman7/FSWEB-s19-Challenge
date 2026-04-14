package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<Retweet> create(@RequestParam Long userId, @RequestParam Long tweetId) {

        return ResponseEntity.ok(
                retweetService.createRetweet(userId, tweetId)
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long userId, @RequestParam Long tweetId) {

        retweetService.deleteRetweet(userId, tweetId);

        return ResponseEntity.ok().build();
    }

}
