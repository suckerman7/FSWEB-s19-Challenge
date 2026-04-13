package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.CreateCommentDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CreateCommentDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(commentService.updateComment(
                id, body.get("content"), Long.parseLong(body.get("userId"))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long userId) {

        commentService.deleteComment(id, userId);

        return ResponseEntity.ok().build();
    }
}
