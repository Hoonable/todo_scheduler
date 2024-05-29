package com.sparta.project2.dto;

import com.sparta.project2.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPostRequestDTO {

    @NotNull
    private String comment;
    private String  userId;
    @NotNull
    private Long todoId;



    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .userId(userId)
                //.todoId(todoId)
                .build();
    }
}
