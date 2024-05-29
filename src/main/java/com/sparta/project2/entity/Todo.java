package com.sparta.project2.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Todo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    private String title;
    private String content;
    private String userName;
    private String password;
    private LocalDateTime createdAt;

    ////////
    @OneToMany
    @JoinColumn(name = "todo_id") // comment 테이블의 todo_id 컬럼
    private List<Comment> commentList = new ArrayList<>();
    ////////1:N연관 관계 설정


    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
