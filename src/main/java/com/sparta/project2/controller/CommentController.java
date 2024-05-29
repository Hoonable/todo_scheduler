package com.sparta.project2.controller;


import com.sparta.project2.CommonResponse;
import com.sparta.project2.dto.CommentPostRequestDTO;
import com.sparta.project2.dto.CommentRequestDTO;
import com.sparta.project2.dto.CommentResponseDTO;
import com.sparta.project2.entity.Comment;
import com.sparta.project2.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/todo/comment")
public class CommentController {
    public final CommentService commentService;

    @PostMapping()
    public ResponseEntity postComment(@RequestBody @Valid CommentPostRequestDTO dto){
        Comment comment = commentService.createComment(dto);
        CommentResponseDTO response = new CommentResponseDTO(comment);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity putComment(@RequestBody @Valid CommentRequestDTO dto){
        Comment comment = comment = commentService.updateComment(dto);
        CommentResponseDTO response = new CommentResponseDTO(comment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<CommonResponse> deleteComment(@RequestBody  @Valid CommentRequestDTO dto){
        commentService.deleteComment(dto);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .msg("삭제에 성공했습니다")
                .statusCode(HttpStatus.OK.value())
                .build());


    }

}
