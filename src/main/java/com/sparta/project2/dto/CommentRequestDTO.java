package com.sparta.project2.dto;

import com.sparta.project2.entity.Comment;
import com.sparta.project2.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {


    private String comment;
    private String  userId;
    @NotNull
    private Long todoId;
    @NotNull
    private Long commentId;



    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .userId(userId)
                //.todoId(todoId)
                .build();
    }
}
