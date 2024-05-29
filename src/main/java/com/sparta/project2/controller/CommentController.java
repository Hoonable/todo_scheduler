package com.sparta.project2.controller;


import com.sparta.project2.dto.CommentRequestDTO;
import com.sparta.project2.dto.CommentResponseDTO;
import com.sparta.project2.entity.Comment;
import com.sparta.project2.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/todo/comment")
public class CommentController {
    public final CommentService commentService;

    @PostMapping()
    public ResponseEntity postComment(@RequestBody CommentRequestDTO dto){
        Comment comment = commentService.createComment(dto);
        CommentResponseDTO response = new CommentResponseDTO(comment);
        return ResponseEntity.ok(response);


    }
}
