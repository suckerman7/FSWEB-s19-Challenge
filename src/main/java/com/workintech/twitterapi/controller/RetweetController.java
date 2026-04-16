package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.RetweetRequestDTO;
import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<Void> retweet(@RequestBody RetweetRequestDTO dto) {

        Long userId = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();

        retweetService.retweet(userId, dto.getTweetId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable Long id) {

        Long userId = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();

        retweetService.deleteRetweet(id, userId);

        return ResponseEntity.ok().build();
    }
}
