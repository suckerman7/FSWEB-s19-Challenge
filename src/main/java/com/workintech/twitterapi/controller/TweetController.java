package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.create.CreateTweetDTO;
import com.workintech.twitterapi.dto.response.TweetResponseDTO;
import com.workintech.twitterapi.dto.create.UpdateTweetDTO;
import com.workintech.twitterapi.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetResponseDTO> create(
            @RequestBody CreateTweetDTO dto) {

        return ResponseEntity.ok(tweetService.createTweet(dto));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<TweetResponseDTO>> findByUserId(
            @RequestParam Long userId) {

        return ResponseEntity.ok(tweetService.findByUserId(userId));
    }

    @GetMapping("/findById")
    public ResponseEntity<TweetResponseDTO> findById(
            @RequestParam long id) {

        return ResponseEntity.ok(tweetService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> update(
            @PathVariable Long id,
            @RequestBody UpdateTweetDTO dto) {

        return ResponseEntity.ok(tweetService.updateTweet(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        tweetService.deleteTweet(id);

        return ResponseEntity.ok().build();
    }
}
