package com.sparta.project2.dto;


import com.sparta.project2.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {

    private Long commentId;
    private String comment;
    private String userId;
    //private Long todoId;
    private LocalDateTime createdAt;
    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.userId = comment.getUserId();
        //this.todoId = comment.getTodoId();
        this.createdAt = comment.getCreatedAt();
    }
}
