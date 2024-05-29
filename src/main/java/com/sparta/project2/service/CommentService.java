package com.sparta.project2.service;


import com.sparta.project2.dto.CommentRequestDTO;
import com.sparta.project2.entity.Comment;
import com.sparta.project2.entity.Todo;
import com.sparta.project2.repository.CommentRepository;
import com.sparta.project2.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public Comment createComment(CommentRequestDTO dto){
        if(dto.getTodoId() == null||dto.getComment() ==null){
            throw new IllegalArgumentException("Invalid request");
        }// 선택 일정의 ID를 입력하지 않거나 댓글 내용 비어 있는경우 Exception

        var newComment = dto.toEntity();
        Todo todo = todoRepository.findById(dto.getTodoId()).orElseThrow(IllegalArgumentException::new);
        //일정이 DB에 없는경우 Exception
        todo.getCommentList().add(newComment);

        //todoRepository.save(todo);

        return commentRepository.save(newComment);

    }

    public Comment updateComment(CommentRequestDTO dto) {
        if(dto.getTodoId() == null||dto.getCommentId() ==null){
            throw new IllegalArgumentException("Invalid request");
        }// 선택 일정이나 댓글의 ID가 입력되지 않은 경우 Exception
        Todo todo = todoRepository.findById(dto.getTodoId()).orElseThrow(IllegalArgumentException::new);
        //선택 일정이 DB에 없는경우 Exception

        Comment comment = todo.getCommentList().stream()
                .filter(x-> Objects.equals(x.getCommentId(), dto.getCommentId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        //선택한 댓글이 DB에 없으면 Exception
        if(!Objects.equals(comment.getUserId(), dto.getUserId())){
            throw new IllegalArgumentException("Invalid request");
        }
        //선택한 댓글의 사용자가 현재 사용자와 일치하지 않은 경우 Exception

        comment.setComment(dto.getComment()); // 댓글 내용만 수정

        return commentRepository.save(comment);




    }
}
