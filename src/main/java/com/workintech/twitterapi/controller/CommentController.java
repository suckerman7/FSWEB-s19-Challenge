package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.CommentResponseDTO;
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

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> create(@RequestBody CreateCommentDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(commentService.updateComment(
                id,
                body.get("content")
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        commentService.deleteComment(id);

        return ResponseEntity.ok().build();
    }
}
