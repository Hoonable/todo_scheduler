package com.sparta.project2.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    private String comment;
    private String userId;
    //private Long todoId;
    private LocalDateTime createdAt;


    @Builder
    public Comment(String comment, String userId, Long todoId) {
        this.comment = comment;
        this.userId = userId;
        //this.todoId = todoId;
        this.createdAt = LocalDateTime.now();

    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
