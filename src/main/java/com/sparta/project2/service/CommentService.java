package com.sparta.project2.service;


import com.sparta.project2.dto.CommentPostRequestDTO;
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

    public Comment createComment(CommentPostRequestDTO dto){

        var newComment = dto.toEntity();
        Todo todo = todoRepository.findById(dto.getTodoId()).orElseThrow(IllegalArgumentException::new);
        //일정이 DB에 없는경우 Exception
        todo.getCommentList().add(newComment);

        //todoRepository.save(todo);

        return commentRepository.save(newComment);

    }

    public Comment updateComment(CommentRequestDTO dto) {
        Comment comment = checkData(dto); //valid 한 데이터인지 체크하고 comment 불러오기

        comment.setComment(dto.getComment()); // 댓글 내용만 수정

        return commentRepository.save(comment);
    }



    public void deleteComment(CommentRequestDTO dto) {
        Comment comment = checkData(dto); //valid 한 데이터인지 체크하고 comment 불러오기

        commentRepository.delete(comment);

    }

    private Comment checkData(CommentRequestDTO dto) {

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
        return comment;
    }

}
